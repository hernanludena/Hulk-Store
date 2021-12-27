/*
 * Metodos para converitr Objetos Dates o Objetos String
 */
package ec.fin.be.tienda.utils;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class FechaUtil implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public static final String FORMATO_FECHA_CORTA = "yyyy-MM-dd";
    public static final String FORMATO_FECHA_CORTA_SLASH = "yyyy/MM/dd";
    public static final String FORMATO_FECHA_DD_MM_YYYY = "dd-MM-yyyy";
    public static final String FORMATO_FECHA_DD_MM_YYYY_SLASH = "dd/MM/yyyy";
    public static final String FORMATO_FECHA_LARGA = "yyyy-MM-dd HH:mm:ss SSS";
    public static final String FORMATO_FECHA_COMPLETA = "EEEE, d MMM yyyy HH:mm:ss";
    public static final String FORMATO_FECHA_MES_CENTURIA = "MM/yyyy";
    public static final String FORMATO_FECHA_MES_DIA = "EEEEE dd MMMMM yyyy";

    private static DatatypeFactory datatypeFactory;

    {
        try {
            datatypeFactory = DatatypeFactory.newInstance();
        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(FechaUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static FechaUtil instancia = null;

    protected FechaUtil() {
        super();
    }

    public static FechaUtil getInstance() {
        if (instancia == null) {
            return instancia = new FechaUtil();
        } else {
            return instancia;
        }

    }

    /**
     * * Encera la hora , minutos, segundos y milisegundos a cero, en la fecha
     * indicada.
     *
     * @param fecha
     * @return Date
     */
    public static Date obtenerInicioDia(Date fecha) {
        LocalDate localDate = new LocalDate(fecha);
        return localDate.toDate();
    }

    /**
     * * Setea la hora a 23 , minutos a 59, segundos a 59 y milisegundos a 999,
     * en la fecha indicada.
     *
     * @param fecha
     * @return Date
     */
    public static Date obtenerFinDia(Date fecha) {
        DateTime dateTime = new DateTime(fecha);
        dateTime = dateTime.hourOfDay().setCopy(23);
        dateTime = dateTime.minuteOfHour().setCopy(59);
        dateTime = dateTime.secondOfMinute().setCopy(59);
        dateTime = dateTime.millisOfSecond().setCopy(999);

        return dateTime.toDate();
    }

    /**
     * Agrega o resta minutos a la fecha indicada.
     *
     * @param fecha
     * @param minutos
     * @return Date
     */
    public static Date sumarRestarMinutos(Date fecha, int minutos) {
        DateTime dateTime = new DateTime(fecha);
        dateTime = dateTime.plusMinutes(minutos);
        return dateTime.toDate();
    }

    /**
     * Agrega o resta dias a la fecha indicada.
     *
     * @param fecha
     * @param dias
     * @return Date
     */
    public static Date sumarRestarDiasFecha(Date fecha, int dias) {
        DateTime dateTime = new DateTime(fecha);
        dateTime = dateTime.plusDays(dias);
        return dateTime.toDate();
    }

    /**
     * Agrega o resta meses a la fecha indicada.
     *
     * @param fecha
     * @param meses
     * @return Date
     */
    public static Date sumarRestarMesesFecha(Date fecha, int meses) {
        DateTime dateTime = new DateTime(fecha);
        dateTime = dateTime.plusMonths(meses);
        return dateTime.toDate();
    }

    /**
     * Asigna la hora , los minutos y los segundos indicados, a la fecha (date).
     *
     * @param date
     * @param hora
     * @param minutos
     * @param segundos
     * @return Date
     */
    public static Date asignarHoraMinutoSegundo(Date date, int hora, int minutos, int segundos) {
        DateTime dateTime = new DateTime(date);
        dateTime = dateTime.hourOfDay().setCopy(hora);
        dateTime = dateTime.minuteOfHour().setCopy(minutos);
        dateTime = dateTime.secondOfMinute().setCopy(segundos);
        return dateTime.toDate();
    }

    /**
     * Genera un string a partir de la fecha indicada (date) en base al formato
     * (yyyy-MM-dd)
     *
     * @param date
     * @return String
     */
    public static String formatearCadenaFechaCorta(Date date) {
        DateTime dateTime = new DateTime(date);
        return DateTimeFormat.forPattern(FORMATO_FECHA_CORTA).print(dateTime);
    }

    /**
     * Genera un string a partir de la fecha indicada (date) en base al formato
     * (yyyy-MM-dd)
     *
     * @param date
     * @return String
     */
    public static String formatearCadenaFechaLargaMesDia(Date date) {
        DateTime dateTime = new DateTime(date);
        return DateTimeFormat.forPattern(FORMATO_FECHA_MES_DIA).print(dateTime);
    }

    /**
     * Genera un string a partir de la fecha indicada (date) en base al formato
     * (yyyy-MM-dd)
     *
     * @param date
     * @return String
     */
    public static String formatearCadenaFechaCortaSlash(Date date) {
        DateTime dateTime = new DateTime(date);
        return DateTimeFormat.forPattern(FORMATO_FECHA_DD_MM_YYYY_SLASH).print(dateTime);
    }

    /**
     * Genera un string a partir de la fecha indicada (date) en base al formato
     * (yyyy-MM-dd HH:mm:ss)
     *
     * @param date
     * @return String
     */
    public static String formatearCadenaFechaLarga(Date date) {
        DateTime dateTime = new DateTime(date);
        return DateTimeFormat.forPattern(FORMATO_FECHA_LARGA).print(dateTime);
    }

    /**
     * Retorna la fecha completa en formato string
     *
     * @param date
     * @return String
     */
    public static String cambiarFechaCompletaAString(Date date) {
        DateTime dateTime = new DateTime(date);
        DateTimeFormatter dtfmrEs = DateTimeFormat.forPattern(FORMATO_FECHA_COMPLETA).withLocale(new Locale("es", "EC"));
        return dtfmrEs.print(dateTime);
    }

    /**
     * Convierte un date en XMLGregorian Calendar utilizada en mesajes XML
     *
     * @param date fecha a transformar
     * @return fecha en formato XMLGregorianCalendar
     */
    public static XMLGregorianCalendar convertirXMLGregorianCalendar(Date date)  {
        GregorianCalendar dateGC = new GregorianCalendar();
        dateGC.setTime(date);
        return datatypeFactory.newXMLGregorianCalendar(dateGC);
    }

    /**
     * Convierte un XMLGregorianCalendar a una fecha simple
     *
     * @param xMLGregorianCalendar fecha XMLGregorianCalendar
     * @return Date
     */
    public static Date convetirXMLGregorianCalendarAFechaCorta(XMLGregorianCalendar xMLGregorianCalendar)  {
        GregorianCalendar cal = xMLGregorianCalendar.toGregorianCalendar();
        return cal.getTime();
    }

    /**
     * Genera un string a partir de la fecha indicada (date) en base al formato
     * (MM/yyyy)
     *
     * @param date
     * @return String
     */
    public static String cambiarFormatoCadenaFechaMesCenturia(Date date) {
        DateTime dateTime = new DateTime(date);
        return DateTimeFormat.forPattern(FORMATO_FECHA_MES_CENTURIA).print(dateTime);
    }

    /**
     * Compara si la fecha dos es mayor a ala fecha uno
     *
     * @param fecIni
     * @param fecFin
     * @return Boolean
     */
    public static Boolean validarFechaInicialFinal(Date fecIni, Date fecFin) {
        if (fecIni != null && fecFin != null) {
            Calendar calIni = Calendar.getInstance();
            calIni.setTime(fecIni);
            Calendar calFin = Calendar.getInstance();
            calFin.setTime(fecFin);
            if (calFin.compareTo(calIni) < 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Resta fechas en dias
     *
     * @param fecIni
     * @param fecFin
     * @return Long
     */
    public static Long restarFechaEnDias(Date fecIni, Date fecFin) {
        Long fechaIni = fecIni.getTime();
        Long fechaFin = fecFin.getTime();
        return (long) Math.floor((double) (fechaFin - fechaIni) / (1000.0d * 60.0d * 60.0d * 24.0d));
    }

    /**
     * Obtiene la diferencia de dos fechas en milisegundos.
     *
     * @param fechaIni
     * @param fechaFin
     * @return Long
     */
    public static Long restartFechasEnMilisegundos(Date fechaIni, Date fechaFin) {
        DateTime dateTime1 = new DateTime(fechaIni);
        DateTime dateTime2 = new DateTime(fechaFin);
        Duration duration = new Duration(dateTime1, dateTime2);

        return duration.getMillis();
    }

    /**
     * Obtener un Date desde un String
     *
     * @param fechaHora
     * @return Date
     */
    public static Date obtenerStringTimestamp(String fechaHora) {
        Date timestamp = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            timestamp = dateFormat.parse(fechaHora);
        } catch (ParseException ex) {
            Logger.getLogger(FechaUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return timestamp;
    }

    /**
     * Elimina las horas de un Calendar.
     *
     * @param calendar
     * @return Calendar
     */
    public static Calendar quitarTiempo(Calendar calendar) {
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.AM_PM, Calendar.AM);
        return calendar;
    }

    /**
     * Obtiene la diferencia de dos fechas en dias.
     *
     * @param fechaIni
     * @param fechaFin
     * @return Long
     */
    public static Long restartFechasEnDias(Date fechaIni, Date fechaFin) {
        DateTime dateTime1 = new DateTime(fechaIni);
        DateTime dateTime2 = new DateTime(fechaFin);
        Duration duration = new Duration(dateTime1, dateTime2);

        return duration.getStandardDays();
    }

    /**
     * Obtiene la diferencia de dos fechas en horas.
     *
     * @param fechaIni
     * @param fechaFin
     * @return Long
     */
    public static Long restartFechasEnHoras(Date fechaIni, Date fechaFin) {
        DateTime dateTime1 = new DateTime(fechaIni);
        DateTime dateTime2 = new DateTime(fechaFin);
        Duration duration = new Duration(dateTime1, dateTime2);

        return duration.getStandardHours();
    }

    /**
     * Obtiene la diferencia de dos fechas en minutos.
     *
     * @param fechaIni
     * @param fechaFin
     * @return Long
     *
     */
    public static Long restartFechasEnMinutos(Date fechaIni, Date fechaFin) {
        DateTime dateTime1 = new DateTime(fechaIni);
        DateTime dateTime2 = new DateTime(fechaFin);
        Duration duration = new Duration(dateTime1, dateTime2);

        return duration.getStandardMinutes();
    }

    /**
     * Obtiene la diferencia de dos fechas en segundos.
     *
     * @param fechaIni
     * @param fechaFin
     * @return Long
     */
    public static Long restartFechasEnSegundos(Date fechaIni, Date fechaFin) {
        DateTime dateTime1 = new DateTime(fechaIni);
        DateTime dateTime2 = new DateTime(fechaFin);
        Duration duration = new Duration(dateTime1, dateTime2);
        return duration.getStandardSeconds();
    }

    public static Long obtenerMinutosEntreFechas(Date fechIni, Date fecFin) {
        long diff = fecFin.getTime() - fechIni.getTime();
        return diff / (60 * 1000);
    }

    public static Long obtenerSegundosEntreFechas(Date fechIni, Date fecFin) {
        long diff = fecFin.getTime() - fechIni.getTime();
        return diff / 1000;
    }

    public static Date passStringToDate(String fecha, String formato) {
        Date dia = new Date();
        try {
            DateFormat format = new SimpleDateFormat(formato, new Locale("es"));
            dia = format.parse(fecha);
        } catch (ParseException ex) {
            Logger.getLogger(FechaUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dia;
    }

    public static String formatearFechaString(Date fecha, String patron) {
        String fechaTras = "";
        try {
            SimpleDateFormat sdfFormatear = new SimpleDateFormat(patron, new Locale("es"));
            fechaTras = sdfFormatear.format(fecha);
        } catch (Exception ex) {
            Logger.getLogger(FechaUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fechaTras;
    }

}
