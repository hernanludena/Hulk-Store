/*
 * Clase para convertir y leer datos EnumTipo de exml
 */
package ec.fin.be.tienda.adaptadores;

import ec.fin.be.tienda.enums.TipoEnum;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class EnumTipoAdaptador extends XmlAdapter<String, TipoEnum> {

    @Override
    public String marshal(TipoEnum value) throws Exception {
        return value.toString();
    }

    @Override
    public TipoEnum unmarshal(String v) throws Exception {
        return TipoEnum.valueOf(v);
    }

}
