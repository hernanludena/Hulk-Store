/*
 * Clase para convertir y leer datos BigDeciaml de exml
 */
package ec.fin.be.tienda.adaptadores;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class BigDecimalAdaptador extends XmlAdapter<String, BigDecimal> {

    @Override
    public String marshal(BigDecimal value) throws Exception {
        return value.setScale(2, RoundingMode.CEILING).toString();
    }

    @Override
    public BigDecimal unmarshal(String v) throws Exception {
        BigDecimal value = new BigDecimal(v);
        return value.setScale(2, RoundingMode.CEILING);
    }

}
