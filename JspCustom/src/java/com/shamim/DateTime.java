package com.shamim;

import java.util.Calendar;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class DateTime extends TagSupport{

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
            out.print(Calendar.getInstance().getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    } 
}
