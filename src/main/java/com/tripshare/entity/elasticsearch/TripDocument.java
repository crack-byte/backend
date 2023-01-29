package com.tripshare.entity.elasticsearch;

import com.tripshare.entity.Trip;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;

@Getter
@Setter
@Document(indexName = "trips")
public class TripDocument {
    @Id
    String trip_id;

    @Field(type = FieldType.Text)
    private String name;
    @Field(type = FieldType.Text)
    private String uuid;
    @Field(type = FieldType.Text)
    private String description;
    @Field(type = FieldType.Text)
    private String category;
    @Field(type = FieldType.Text)
    private String status;
    @Field(type = FieldType.Date)
    private LocalDateTime startDate;
    @Field(type = FieldType.Date)
    private LocalDateTime endDate;

    @Field(type = FieldType.Keyword, index = false)
    private String[] participants;

    public TripDocument(String trip_id, String name, String uuid, String description, String category, String status,
                        LocalDateTime startDate, LocalDateTime endDate) {
        this.trip_id = trip_id;
        this.name = name;
        this.uuid = uuid;
        this.description = description;
        this.category = category;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public TripDocument(Trip trip) {
        this.trip_id = String.valueOf(trip.getId());
        this.name = trip.getName();
        this.uuid = trip.getUuid();
        this.description = trip.getDescription();
        this.category = trip.getCategory();
        this.status = trip.getStatus();
        this.startDate = trip.getStartDate();
        this.endDate = trip.getEndDate();
    }

    public TripDocument() {
    }
}
