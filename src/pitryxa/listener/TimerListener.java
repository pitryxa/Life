package pitryxa.listener;

import pitryxa.service.MainService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static pitryxa.parameters.Parameters.TIMER_DELAY_MILLISECONDS;

public class TimerListener implements ActionListener {

    private final MainService mainService;

    public TimerListener(MainService mainService) {
        this.mainService = mainService;
        new Timer(TIMER_DELAY_MILLISECONDS, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainService.timerAction();
    }
}
