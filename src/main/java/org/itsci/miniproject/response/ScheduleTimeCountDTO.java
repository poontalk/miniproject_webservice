package org.itsci.miniproject.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ScheduleTimeCountDTO {
    private LocalDateTime scheduleTime;
    private Long count;
    private int timeSpend;
}
