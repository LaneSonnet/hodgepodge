package com.mudfish.common.xml.xstream.converter;

import com.mudfish.common.xml.xstream.User;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

/**
 * Created by JiangWeiGen on 2018/11/18 0018.
 */
public class DateConverter implements Converter {
    @Override
    public void marshal(Object o, HierarchicalStreamWriter hierarchicalStreamWriter, MarshallingContext marshallingContext) {
        Date birthday = (Date) o;
        hierarchicalStreamWriter.startNode("birthday");
        String date = new SimpleDateFormat("yyyy-MM-dd").format(birthday);
        hierarchicalStreamWriter.setValue(date);
        hierarchicalStreamWriter.endNode();
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader hierarchicalStreamReader, UnmarshallingContext unmarshallingContext) {
        String birthday = hierarchicalStreamReader.getValue();
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(birthday);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    @Override
    public boolean canConvert(Class aClass) {
        System.out.println(aClass.getName());
        return true;
    }
}
