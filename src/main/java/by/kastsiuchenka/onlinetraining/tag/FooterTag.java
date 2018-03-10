package by.kastsiuchenka.onlinetraining.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;

@SuppressWarnings("serial")
public class FooterTag extends BodyTagSupport {
    @Override
    public int doStartTag() throws JspException {
        return EVAL_BODY_BUFFERED;
//        return EVAL_BODY_INCLUDE;
//        return SKIP_BODY;
    }

    @Override
    public int doAfterBody() throws JspException {
        BodyContent content = this.getBodyContent();
        String body = content.getString();
        String res = "<footer class=\"container-fluid text-center\">" + body + "</footer>";

        try {
            JspWriter out = content.getEnclosingWriter();
            out.write(res);

        } catch (IOException e) {
            throw new JspTagException(e.getMessage());
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}
