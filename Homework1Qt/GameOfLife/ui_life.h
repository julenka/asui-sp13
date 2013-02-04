/********************************************************************************
** Form generated from reading UI file 'life.ui'
**
** Created: Sun Jan 20 23:05:31 2013
**      by: Qt User Interface Compiler version 5.0.0
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_LIFE_H
#define UI_LIFE_H

#include <QtCore/QVariant>
#include <QtWidgets/QAction>
#include <QtWidgets/QApplication>
#include <QtWidgets/QButtonGroup>
#include <QtWidgets/QHeaderView>
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QMenu>
#include <QtWidgets/QMenuBar>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QStatusBar>
#include <QtWidgets/QVBoxLayout>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_life
{
public:
    QWidget *centralWidget;
    QWidget *layoutWidget;
    QVBoxLayout *verticalLayout;
    QPushButton *startButton;
    QMenuBar *menuBar;
    QMenu *menuFile;
    QStatusBar *statusBar;

    void setupUi(QMainWindow *life)
    {
        if (life->objectName().isEmpty())
            life->setObjectName(QStringLiteral("life"));
        life->resize(409, 425);
        centralWidget = new QWidget(life);
        centralWidget->setObjectName(QStringLiteral("centralWidget"));
        layoutWidget = new QWidget(centralWidget);
        layoutWidget->setObjectName(QStringLiteral("layoutWidget"));
        layoutWidget->setGeometry(QRect(10, 10, 391, 371));
        verticalLayout = new QVBoxLayout(layoutWidget);
        verticalLayout->setSpacing(6);
        verticalLayout->setContentsMargins(11, 11, 11, 11);
        verticalLayout->setObjectName(QStringLiteral("verticalLayout"));
        verticalLayout->setContentsMargins(0, 0, 0, 0);
        startButton = new QPushButton(layoutWidget);
        startButton->setObjectName(QStringLiteral("startButton"));

        verticalLayout->addWidget(startButton);

        life->setCentralWidget(centralWidget);
        menuBar = new QMenuBar(life);
        menuBar->setObjectName(QStringLiteral("menuBar"));
        menuBar->setGeometry(QRect(0, 0, 409, 22));
        menuFile = new QMenu(menuBar);
        menuFile->setObjectName(QStringLiteral("menuFile"));
        life->setMenuBar(menuBar);
        statusBar = new QStatusBar(life);
        statusBar->setObjectName(QStringLiteral("statusBar"));
        life->setStatusBar(statusBar);

        menuBar->addAction(menuFile->menuAction());

        retranslateUi(life);

        QMetaObject::connectSlotsByName(life);
    } // setupUi

    void retranslateUi(QMainWindow *life)
    {
        life->setWindowTitle(QApplication::translate("life", "life", 0));
        startButton->setText(QApplication::translate("life", "start", 0));
        menuFile->setTitle(QApplication::translate("life", "File", 0));
    } // retranslateUi

};

namespace Ui {
    class life: public Ui_life {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_LIFE_H
