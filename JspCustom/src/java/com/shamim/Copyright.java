package com.shamim;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class Copyright extends TagSupport{
    @Override
    public int doStartTag() throws JspException {
        
        JspWriter out = pageContext.getOut();
        try {
            out.print("@Copyright | All rights reserved by Shamim");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }   
}
