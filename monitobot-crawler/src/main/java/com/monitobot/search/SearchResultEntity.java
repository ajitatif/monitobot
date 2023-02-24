package com.monitobot.search;

import io.quarkiverse.hibernate.types.json.JsonType;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@gmail.com)
 */
@Entity
@Table(name = "search_results")
public class SearchResultEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(generator = "sq_search_results_id", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "sq_search_results_id", sequenceName = "sq_search_results_id", allocationSize = 1)
    public Long id;
    @ManyToOne
    @JoinColumn(name = "track_id")
    public TrackEntity track;
    @Column(name = "search_engine")
    public String searchEngine;
    @Column(name = "created_on_utc")
    public LocalDateTime createdOnUtc;
    @Column(name = "status_code")
    public Integer statusCode;
    @Column(name = "raw_data")
    public String rawData;
}
