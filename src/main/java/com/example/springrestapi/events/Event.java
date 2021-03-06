package com.example.springrestapi.events;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;


/**
 * In case of referencing between entities, using default @EqualsAndHashCode stack overflow.
 * Therefore, redefine the way of checking equality is the best practice as below.
 * Also, you shouldn't use @Data annotation because it uses default @EqualsAndHashCode
 */
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Event {

    @Id @GeneratedValue
    private Integer id;
    private String name;
    private String description;
    private LocalDateTime beginEnrollmentDateTime;
    private LocalDateTime closeEnrollmentDateTime;
    private LocalDateTime beginEventDateTime;
    private LocalDateTime endEventDateTime;
    private String location; // (optional)
    private int basePrice; // (optional)
    private int maxPrice;  // (optional)
    private int limitOfEnrollment;
    private boolean offline;
    private boolean free;
    @Enumerated(EnumType.STRING)
    private EventStatus eventStatus = EventStatus.DRAFT;

    public void update() {
        // Update free
        if(this.basePrice == 0 && this.maxPrice == 0) {
            this.free = true;
        } else {
            this.free = false;
        }

        // Update offline
        if(this.location == null || this.location.isBlank()) {
            this.offline = false;
        } else {
            this.offline = true;
        }
    }
}