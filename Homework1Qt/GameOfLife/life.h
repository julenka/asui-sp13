#ifndef LIFE_H
#define LIFE_H

#include <QMainWindow>

namespace Ui {
class life;
class LifeGrid;
}

class LifeGrid : public QWidget
{
    Q_OBJECT

public:

    LifeGrid(QWidget *parent = 0);
    ~LifeGrid();

    QSize minimumSizeHint() const;
    QSize sizeHint() const;

    void startSimulation();
    void stopSimulation();

protected:

    void paintEvent(QPaintEvent *event);
    void mousePressEvent(QMouseEvent *event);


private slots:
    void timer_timeout();

private:


    int m_numRows;
    int m_numCols;

    QTimer* m_timer;
    std::vector<int> m_PixelData;
};


class life : public QMainWindow
{
    Q_OBJECT
    
public:
    explicit life(QWidget *parent = 0);
    ~life();
    
private slots:
    void on_startButton_clicked();

private:
    Ui::life *ui;
    LifeGrid*m_pLifeGrid;
    bool    m_running;

};


#endif // LIFE_H
