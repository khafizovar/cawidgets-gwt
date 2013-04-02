package org.tatasu.gwt.server.treemapchart;

import java.io.StringReader;
import java.io.StringWriter;
import java.sql.Connection;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.tatasu.gwt.client.chart.treemapchart.TreeMapChartData;
import org.tatasu.gwt.client.chart.treemapchart.options.TreeMapChartOptions;

/**
 * Прокси класс обертка связанный с таблицей данных в бд (если при соединении
 * таблица не обнаружена будет создаваться объект со стандартными опциями)
 * Попытка выборки данных конфигурации происходит на момент создания обертки и
 * на базе выбранного xml файла формируется объект с опциями. Сразу после
 * выборки объект Connection закрывается. Для обновления опций графика из БД
 * необходимо передать ему новый объект Connection
 * 
 * @author HafizovAR
 * 
 */
public class TreeMapChartDataWrapper {

	public TreeMapChartDataWrapper() {}

	/**
	 * Конвертирует объект TreeMapChartOptions  в XML String
	 * @param data	объект для конвертации в xml строку
	 * @return
	 * @throws JAXBException
	 */
	public static synchronized String dataToXmlString(TreeMapChartOptions options) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(TreeMapChartOptions.class);
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		
		StringWriter writer = new StringWriter();
		m.marshal(options, writer);
		return writer.toString();		
	}
	/**
	 * Конвертирует xml строку в объект опций графика TreemapChartOptions
	 * @param xmlString	xml строка с данными
	 * @return
	 * @throws JAXBException
	 */
	public static synchronized TreeMapChartOptions xmlToOptions(String xmlString) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(TreeMapChartOptions.class);
		Unmarshaller um = context.createUnmarshaller();
		StringReader reader = new StringReader(xmlString);
		TreeMapChartOptions options = (TreeMapChartOptions) um.unmarshal(reader);
		return options;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			
			TreeMapChartOptions options = new TreeMapChartOptions();
			String xmlOptions = dataToXmlString(options); 
			System.out.println(xmlOptions);
			
			System.out.println(xmlToOptions(xmlOptions));
			
			
			/*TreeMapChartDataList dataStore = new TreeMapChartDataList();
			TreeMapChartData data = new TreeMapChartData("test", "parent", 123, 2345);
			TreeMapChartData data2 = new TreeMapChartData("test2", "parent2", 1324, 2356);
			dataStore.add(data);
			dataStore.add(data2);
			JAXBContext context = JAXBContext.newInstance(TreeMapChartDataList.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			
			StringWriter writer = new StringWriter();
			m.marshal(dataStore, writer);
			System.out.println(writer.toString());*/
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
}
