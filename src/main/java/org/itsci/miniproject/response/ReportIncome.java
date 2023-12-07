package org.itsci.miniproject.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReportIncome {
    private String reportDate;
    private double totalIncome;
}
