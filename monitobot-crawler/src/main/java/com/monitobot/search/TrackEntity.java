package com.monitobot.search;

import io.quarkiverse.hibernate.types.json.JsonTypes;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@gmail.com)
 */
@Entity
@Table(name = "tracks")
public class TrackEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(generator = "sq_tracks_id", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "sq_tracks_id", sequenceName = "sq_tracks_id", allocationSize = 1)
    public Long id;
    @Column(name = "public_id")
    public String publicId;
    @Column(name = "created_on_utc")
    public LocalDateTime createdOnUtc;
    @Column(name = "last_run_on_utc")
    public LocalDateTime lastRunOnUtc;
    @Type(type = JsonTypes.JSON)
    @Column(name = "search_criteria", columnDefinition = JsonTypes.JSON_BIN)
    @Convert(disableConversion = true)
    public SearchCriteria searchCriteria;
}
