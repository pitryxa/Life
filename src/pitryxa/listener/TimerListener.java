package pitryxa.listener;

import pitryxa.service.AppService;
import pitryxa.window.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerListener implements ActionListener {

    private Window window;
    private AppService appService;

    public TimerListener(AppService appService) {
        this.appService = appService;
        this.window = appService.getWindow();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
