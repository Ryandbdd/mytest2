package com.itheima.es.test;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Test;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TestElasticSearch {

    @Test
    //创建索引
    public void test1() throws Exception {
        //创建客户端访问对象
        /**
         * Settings表示集群的设置
         * EMPTY：表示没有集群的配置
         * Settings.EMPTY
         */
        TransportClient client = new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
//创建文档对象
        // 方案一：组织Document数据
        //Map<String,Object> map = new HashMap<String,Object>();
        //map.put("id",1);
        //map.put("title","3-ElasticSearch是一个基于Lucene的搜索服务器");
        //map.put("content","3-它提供了一个分布式多用户能力的全文搜索引擎，基于RESTful web接口。Elasticsearch是用Java开发的，并作为Apache许可条款下的开放源码发布，是当前流行的企业级搜索引擎。");
        // 方案二：组织Document数据（使用ES的api构建json）
        // {id:1,title:"xxx",content:"xxxxxx"}
        XContentBuilder builder = XContentFactory.jsonBuilder().startObject()
                .field("id", 1)
                .field("title", "ElasticSearch是一个基于Lucene的搜索服务器。")
                .field("content", "它提供了一个分布式多用户能力的全文搜索引擎，基于RESTful web接口。Elasticsearch是用Java开发的，并作为Apache许可条款下的开放源码发布，" +
                        "是当前流行的企业级搜索引擎。设计用于云计算中，能够达到实时搜索，稳定，可靠，快速，安装使用方便。")
                .endObject();
        //创建索引、创建文档类型、设置唯一主键。同时创建文档
        //IndexResponse indexResponse = client.prepareIndex("blog1", "article", "3").setSource(map).get();//执行动作
        IndexResponse indexResponse = client.prepareIndex("blog1", "article", "4").setSource(builder).get();//执行动作
        System.out.println(indexResponse.getId());
        System.out.println(indexResponse.getIndex());
        System.out.println(indexResponse.getType());
        System.out.println(indexResponse.getVersion());
        //关闭资源
        client.close();
    }
    @Test
//查询全部
    public void test2() throws Exception {
        //创建客户端访问对象
        TransportClient client = new PreBuiltTransportClient(Settings.EMPTY).addTransportAddress(
                new InetSocketTransportAddress(
                        InetAddress.getByName("127.0.0.1"), 9300));
        //设置查询条件（QueryBuilders.matchAllQuery()：查询所有）
        SearchResponse searchResponse = client.prepareSearch("blog1").setTypes("article")
                .setQuery(QueryBuilders.matchAllQuery()).get();
        //处理结果
        SearchHits hits = searchResponse.getHits(); //获得命中目标，即查询到了多少个对象
        System.out.println("共查询"+hits.getTotalHits()+"条");
        Iterator<SearchHit> ite = hits.iterator();
        while(ite.hasNext()){
            SearchHit searchHit = ite.next();
            System.out.println(searchHit.getSourceAsString());
            System.out.println(searchHit.getSource().get("title"));
        }
        //关闭资源
        client.close();
    }
    // id查询
    @Test
    public void queryId() throws Exception {
        // 创建Client，连接ES
        // Settings.EMPTY：表示ES在非集群的环境运行。
        TransportClient client = new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"),9300));// 需要指定ES的地址和端口


        GetResponse getResponse = client.prepareGet("blog1", "article", "1").get();
        String sourceAsString = getResponse.getSourceAsString();
        System.out.println(sourceAsString);


        client.close();
    }
//    4.2.5.模糊查询（通配符查询）
//            *：表示所有的任意的多个字符组成
//?：表示1个任意的字符
    @Test
    //模糊查询
    public void test5() throws Exception {
        //创建客户端访问对象
        TransportClient client = new PreBuiltTransportClient(Settings.EMPTY).addTransportAddress(
                new InetSocketTransportAddress(
                        InetAddress.getByName("127.0.0.1"), 9300));
        //设置查询条件
        SearchResponse searchResponse = client.prepareSearch("blog1").setTypes("article")
                .setQuery(QueryBuilders.wildcardQuery("title","*搜索*")).get();
        //处理结果
        SearchHits hits = searchResponse.getHits(); //获得命中目标，即查询到了多少个对象
        System.out.println("共查询"+hits.getTotalHits()+"条");
        Iterator<SearchHit> ite = hits.iterator();
        while(ite.hasNext()){
            SearchHit searchHit = ite.next();
            System.out.println(searchHit.getSourceAsString());
            System.out.println(searchHit.getSource().get("title"));
        }
        //关闭资源
        client.close();
    }

}