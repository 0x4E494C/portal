package tech.nilvalue.portal.ErrorHandler;

import java.util.List;


// Будет неактуально после рефакторинга ErrorHandler
public record ValidationErrorResponse<V>(List<Violation> violationList) {
}


record Violation(String field, String message){
}
