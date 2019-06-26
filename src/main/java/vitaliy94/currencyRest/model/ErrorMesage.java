package vitaliy94.currencyRest.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * used to return errors like json, not plain text
 */
@Data
@AllArgsConstructor
public class ErrorMesage
{
    String error;
}
