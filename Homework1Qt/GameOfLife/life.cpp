#include "life.h"
#include "ui_life.h"
#include <QPainter>
#include <QTimer>
#include <QMouseEvent>
#include <QPoint>

life::life(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::life),
    m_running(false)
{
    ui->setupUi(this);
    m_pLifeGrid = new LifeGrid;
    ui->verticalLayout->addWidget(m_pLifeGrid, 0, Qt::AlignCenter);

}

life::~life()
{
    delete ui;
}



LifeGrid::LifeGrid(QWidget * parent) :
    QWidget(parent)
{
    setBackgroundRole(QPalette::Base);
    setAutoFillBackground(true);

    m_numRows = 20;
    m_numCols = 20;
    // initialize grid
    m_PixelData.clear();

    for(int i = 0; i < m_numRows * m_numCols; i++)
    {

        m_PixelData.push_back(0);
    }

    // initialize timer
    m_timer  = new QTimer(this);
    connect(m_timer, SIGNAL(timeout()),
            this, SLOT(timer_timeout()));

}

LifeGrid::~LifeGrid()
{
    delete m_timer;
}

void LifeGrid::mousePressEvent(QMouseEvent * event)
{
    // get x, y relative to control
    QPoint p = event->pos();
    int stepX = width() / m_numCols;
    int stepY = height() / m_numRows;
    int tileX = p.x() / stepX;
    int tileY = p.y() / stepY;
    m_PixelData[tileY*m_numRows + tileX] = (m_PixelData[tileY*m_numRows + tileX] + 1) % 2;

    update();
}

void LifeGrid::timer_timeout()
{
    // update the grid
    int neighborCounts[m_numRows * m_numCols];
    for(int y = 0; y < m_numRows; y++)
    {
        for(int x = 0; x < m_numCols; x++)
        {
            int livingNeighbors = 0;
            for(int yy = -1; yy <=1; yy++)
            {
                for(int xx = -1; xx <= 1; xx++)
                {
                    int xxx = x + xx;
                    int yyy = y + yy;
                    if(xx == 0 && yy == 0) continue;
                    if(xxx < 0) xxx = m_numCols - 1;
                    if(yyy < 0) yyy = m_numRows - 1;
                    if(xxx >= m_numCols) xxx = 0;
                    if(yyy >= m_numRows) yyy = 0;
                    livingNeighbors += m_PixelData[yyy * m_numCols + xxx];
                }
            }
            neighborCounts[y * m_numCols + x] = livingNeighbors;
        }
    }
    for(int y = 0; y < m_numRows; y++)
    {
        for(int x = 0; x < m_numCols; x++)
        {
            int livingNeighbors = neighborCounts[y * m_numCols + x];
            int nextValue = 0;
            if(m_PixelData[y*m_numRows + x] > 0)
            {
                //Any live cell with fewer than two live neighbours dies, as if caused by under-population.
                //Any live cell with two or three live neighbours lives on to the next generation.
                //Any live cell with more than three live neighbours dies, as if by overcrowding.
                // living cell
                if(livingNeighbors == 2 || livingNeighbors == 3)
                    nextValue = 1;
            } else
            {
                // dead cell
                // Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
                if(livingNeighbors == 3)
                    nextValue = 1;
            }
            m_PixelData[y*m_numRows + x] = nextValue;
        }
    }

    update();
}

QSize LifeGrid::minimumSizeHint() const
{
    return QSize(400, 400);
}

QSize LifeGrid::sizeHint() const
{
    return QSize(400, 400);
}

void LifeGrid::paintEvent(QPaintEvent* )
{
    QPainter painter(this);
    painter.setPen(QPen(Qt::black));
    int stepX = width() / m_numCols;
    int stepY = height() / m_numRows;

    for(int y = 0; y < m_numRows; y++)
    {
        for(int x = 0; x < m_numCols; x++)
        {
            if(m_PixelData[y*m_numRows + x] > 0)
            {
                painter.setBrush(QBrush(Qt::black, Qt::SolidPattern));
            } else
            {
                painter.setBrush(QBrush(Qt::white, Qt::SolidPattern));
            }
            painter.drawRect(QRect(x * stepX, y * stepY, stepX, stepY));
        }
    }
}

void LifeGrid::startSimulation()
{
    m_timer->start(80);
}

void LifeGrid::stopSimulation()
{
    m_timer->stop();
}

void life::on_startButton_clicked()
{
    // start a timer that updates periodically

    if(m_running)
    {
        m_pLifeGrid->stopSimulation();
        ui->startButton->setText("Start");
    }
    else
    {
        m_pLifeGrid->startSimulation();
        ui->startButton->setText("Stop");
    }
    m_running = !m_running;
}
