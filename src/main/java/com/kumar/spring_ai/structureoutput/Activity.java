package com.kumar.spring_ai.structureoutput;


import java.time.LocalDate;

public record Activity(String activity, String location, LocalDate day, String time) {
}