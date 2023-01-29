package com.tripshare.repository.elasticsearch;

import com.tripshare.entity.Trip;
import com.tripshare.entity.elasticsearch.TripDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface TripElasticRepository extends ElasticsearchRepository<TripDocument, String> {
}
