package com.robertreed4501.chores.model.db;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Time;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Assignment {
    @Id
    @SequenceGenerator(
            name = "assignment_sequence",
            sequenceName = "assignment_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "assignment_sequence"
    )
    private Long id;
    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "user_id",
            referencedColumnName = "id"
    )
    private User user;
    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "chore_id",
            referencedColumnName = "id"
    )
    private Chore chore;
    @OneToMany(mappedBy = "assignment")
    @JsonManagedReference
    private List<Receipt> receipts;
    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private boolean active = true;
    private LocalDateTime start;
    private LocalDateTime end;
    private Boolean done = false;
    private Boolean approved = false;

    public Assignment(User user, Chore chore) {

        LocalDateTime currentTime = LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT);
        LocalDateTime prevMonday = currentTime.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDateTime nextSundayNight = currentTime.with(TemporalAdjusters.next(DayOfWeek.MONDAY)).minusSeconds(1);

        this.user = user;
        this.chore = chore;
        this.start = prevMonday;
        this.end = nextSundayNight;
    }
}
