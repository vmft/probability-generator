# probability-generator

Генератор случайных величин по различным законам распределения с визуализацией в виде гистограммы. Приложение разработано на языке Java с использованием JavaFX для отрисовки GUI.

## Начало работы
Вам понадобится:
1. Java SE Development Kit 8
2. IntelliJ IDEA
3. Apache Maven (входит в инструментарий IntelliJ IDEA)
4. Git
5. Аккаунт на GitHub (для добавления результатов своего труда в общий проект)

## Создание репозитория
Для работы над проектом вам нужно создать свой репозиторий, основанный на данном. Для этого залогиньтесь под своим профилем на GitHub и в правом верхнем углу на странице данного проекта нажмите кнопку "Fork".
После создания форка проекта запустите IntelliJ IDEA и выберите пункт "Checkout from Version Control". В поле "URL" вставьте ссылку **на свой** репозиторий (форк данного проекта).
После синхронизации локального репозитория с удаленным вы можете начать разработку. Также рекомендуется добавить оригинальный репозиторий проекта в качестве upstream репозитория (Git - Remotes - Add) для синхронизации возможных изменений.

## Работа над проектом
Проект представляет из себя JavaFX приложение. Вам предоставлен интерфейс Generator в пакете ru.psuti.courseproject.core.generator, конкретная реализация которого в том же пакете и будет вашим генератором.
Кроме того, для интергации вашего генератора с GUI, вам необходимо внести следующие изменения в класс Controller:
1. Создать константу с названием своего распределения
```
private static final String EXPONENTIAL_DISTRIBUTION = "Экспоненциальное распределение";
```
2. Добавить в метод initialize(), в отмеченное комментарием место эту константу
```
distributionComboBox.getItems().addAll(
    GAMMA_DISTRIBUTION,
    NORMAL_DISTRIBUTION,
    // ПОТОМ ДОБАВИТЬ НАЗВАНИЕ СВОЕГО РАСПРЕДЕЛЕНИЯ СЮДА
    EXPONENTIAL_DISTRIBUTION
);
```
3. Добавить в метод showHistogram() несколько строк кода для обработки выбора своего распределения в ComboBox по аналогии с уже добавленными.
```
if (distributionComboBox.getValue() != null) {
    switch (distributionComboBox.getValue()) {
        case GAMMA_DISTRIBUTION:
            GammaDistributionGenerator gammaDistributionGenerator = new GammaDistributionGenerator();
            setupHistogram(gammaDistributionGenerator);
            updateTable(gammaDistributionGenerator);
            break;
        //И СЮДА
        case NORMAL_DISTRIBUTION:
            NormalDistributionGenerator normalDistributionGenerator = new NormalDistributionGenerator();
            setupHistogram(normalDistributionGenerator);
            updateTable(normalDistributionGenerator);
            break;
        default:
            break;
    }
}
```
Класс Controller находится в пакете ru.psuti.courseproject.core.gui.

## Сборка и запуск приложения
Вы можете запускать приложение из IDE указывая метод Main, который находится в пакете ru.psuti.courseproject.core.gui, либо выполнить сборку с использованием Maven.

## Сборка в .jar
Создать на основе шаблона Maven новую конфигурацию (Run - Edit Configurations - Add) и добавить в аргументы командной строки (Command line):
```
jfx:jar
```
Сохранить конфигурацию и запустить. Собранный .jar будет находиться в target/jfx/app/lib/.

## Внесение изменений в основной репозиторий
В процессе разработки вы можете в неограниченном количестве коммитить в свой репозиторий, но после окончания разработки возникнет необходимость добавить свою работу в общий проект. Для этого необходимо воспользоваться кнопкой "New pull request" на странице своего GitHub репозитория. После рассмотрения реквеста владельцем оригинального репозитория ваша работа будет добавлена в общий проект.
