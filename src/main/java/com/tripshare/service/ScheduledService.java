//package com.tripshare.service;
//
//import co.elastic.clients.elasticsearch.core.IndexRequest;
//import com.tripshare.entity.Trip;
//import com.tripshare.repository.TripRepository;
//import com.tripshare.repository.elasticsearch.TripElasticRepository;
//import com.tripshare.util.Util;
//import org.elasticsearch.client.Request;
//import org.elasticsearch.client.RestClient;
//import org.elasticsearch.xcontent.XContentType;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//@Service
//public class ScheduledService {
////    @Autowired
////    private TripElasticRepository tripElasticRepository;
////
////    @Autowired
////    private TripRepository tripRepository;
////    @Autowired
////    private ElasticsearchOperations elasticsearchOperations;
////
////    @Scheduled(initialDelay = 3000,fixedRate = 60*60000)
////    public void updateElastic(){
////        List<Trip> all = tripRepository.findAll();
////        if(!all.isEmpty()){
////
////        }
////
////    }
//}
