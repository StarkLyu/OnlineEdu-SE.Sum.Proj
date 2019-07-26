package com.se231.onlineedu.message.response;

import com.se231.onlineedu.model.Paper;
import com.se231.onlineedu.model.PaperAnswerState;

/**
 * Response of paper finish status
 *
 * mark the state of paper finishing.
 *
 * @author Zhe Li
 *
 * @date 2019/7/3
 */
public class PaperFinish {
    private PaperAnswerState state;

    private Paper paper;

    public PaperFinish(Paper paper,PaperAnswerState state){
        this.paper=paper;
        this.state=state;
    }

    public Paper getPaper() {
        return paper;
    }

    public void setPaper(Paper paper) {
        this.paper = paper;
    }

    public PaperAnswerState getState() {
        return state;
    }

    public void setState(PaperAnswerState state) {
        this.state = state;
    }
}
