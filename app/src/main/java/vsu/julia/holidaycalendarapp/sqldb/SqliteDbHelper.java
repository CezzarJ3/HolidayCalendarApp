package vsu.julia.holidaycalendarapp.sqldb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqliteDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Holidays.db";

    private static final String SQL_CREATE_IDO_CODES_TABLE =
            "CREATE TABLE " + HolidayDBContract.ISOCodeEntry.TABLE_NAME + " ("
                    + HolidayDBContract.ISOCodeEntry._ID + " INTEGER PRIMARY KEY, "
                    + HolidayDBContract.ISOCodeEntry.CODE_COLUMN + " TEXT NOT NULL UNIQUE, "
                    + HolidayDBContract.ISOCodeEntry.COUNTY_COLUMN + " TEXT NOT NULL UNIQUE);";

    private static final String SQL_CREATE_HOLIDAY_TABLE =
            "CREATE TABLE " + HolidayDBContract.HolidayEntry.TABLE_NAME + " ("
                    + HolidayDBContract.HolidayEntry._ID + " INTEGER PRIMARY KEY, "
                    + HolidayDBContract.HolidayEntry.NAME_COLUMN + " TEXT NOT NULL, "
                    + HolidayDBContract.HolidayEntry.DESCRIPTION_COLUMN + " TEXT NOT NULL, "
                    + HolidayDBContract.HolidayEntry.COUNTRY_COLUMN + " TEXT NOT NULL, "
                    + HolidayDBContract.HolidayEntry.DATE_COLUMN + " TEXT NOT NULL," +
                    " UNIQUE (" + HolidayDBContract.HolidayEntry.NAME_COLUMN +
                    ", " + HolidayDBContract.HolidayEntry.DESCRIPTION_COLUMN +
                    ", " + HolidayDBContract.HolidayEntry.COUNTRY_COLUMN +
                    ", " + HolidayDBContract.HolidayEntry.DATE_COLUMN + "));";

    private static final String SQL_INSERT_ISO_VALUES =
            "INSERT INTO " + HolidayDBContract.ISOCodeEntry.TABLE_NAME + " (" + HolidayDBContract.ISOCodeEntry.CODE_COLUMN + "," + HolidayDBContract.ISOCodeEntry.COUNTY_COLUMN + ")" +
                    " VALUES ('AF', 'Афганистан'),\n" +
                    "('AX', 'Аландские острова'),\n" +
                    "('AL', 'Албания'),\n" +
                    "('DZ', 'Алжир'),\n" +
                    "('AS', 'Американское Самоа'),\n" +
                    "('AD', 'Андорра'),\n" +
                    "('AO', 'Ангола'),\n" +
                    "('AI', 'Ангилья'),\n" +
                    "('AQ', 'Антарктика'),\n" +
                    "('AG', 'Антигуа и Барбуда'),\n" +
                    "('AR', 'Аргентина'),\n" +
                    "('AM', 'Армения'),\n" +
                    "('AW', 'Аруба'),\n" +
                    "('AU', 'Австралия'),\n" +
                    "('AT', 'Австрия'),\n" +
                    "('AZ', 'Азербайджан'),\n" +
                    "('BS', 'Багамы'),\n" +
                    "('BH', 'Бахрейн'),\n" +
                    "('BD', 'Багладеш'),\n" +
                    "('BB', 'Барбадос'),\n" +
                    "('BY', 'Беларусь'),\n" +
                    "('BE', 'Белгия'),\n" +
                    "('BZ', 'Белиз'),\n" +
                    "('BJ', 'Бенин'),\n" +
                    "('BM', 'Бермуды'),\n" +
                    "('BT', 'Бутан'),\n" +
                    "('BO', 'Боливия'),\n" +
                    "('BA', 'Босния и Герцеговина'),\n" +
                    "('BW', 'Ботсвана'),\n" +
                    "('BV', 'Остров Буве'),\n" +
                    "('BR', 'Бразилия'),\n" +
                    "('IO', 'Британская территория Индийского океана'),\n" +
                    "('BN', 'Бруней-Даруссалам'),\n" +
                    "('BG', 'Болгария'),\n" +
                    "('BF', 'Буркина-Фасо'),\n" +
                    "('BI', 'Бурунди'),\n" +
                    "('KH', 'Камбоджа'),\n" +
                    "('CM', 'Камерун'),\n" +
                    "('CA', 'Канада'),\n" +
                    "('CV', 'Кабо-Верде'),\n" +
                    "('KY', 'Каймановы острова'),\n" +
                    "('CF', 'Центрально-Африканская Республика'),\n" +
                    "('TD', 'Чад'),\n" +
                    "('CL', 'Чили'),\n" +
                    "('CN', 'Китай'),\n" +
                    "('CX', 'Остров Рождества'),\n" +
                    "('CC', 'Кокосовые (Килинг) острова'),\n" +
                    "('CO', 'Колумбия'),\n" +
                    "('KM', 'Коморы'),\n" +
                    "('CG', 'Конго'),\n" +
                    "('CD', 'Конго, Демократическая Республика'),\n" +
                    "('CK', 'Острова Кука'),\n" +
                    "('CR', 'Коста-Рика'),\n" +
                    "('CI', 'Республика Кот-д’Ивуар'),\n" +
                    "('HR', 'Хорватия'),\n" +
                    "('CU', 'Куба'),\n" +
                    "('CY', 'Кипр'),\n" +
                    "('CZ', 'Чешская Республика'),\n" +
                    "('DK', 'Дания'),\n" +
                    "('DJ', 'Джибути'),\n" +
                    "('DM', 'Доминика'),\n" +
                    "('DO', 'Доминиканская Республика'),\n" +
                    "('EC', 'Эквадор'),\n" +
                    "('EG', 'Египет'),\n" +
                    "('SV', 'Сальвадор'),\n" +
                    "('GQ', 'Экваториальная Гвинея'),\n" +
                    "('ER', 'Эритрея'),\n" +
                    "('EE', 'Эстония'),\n" +
                    "('ET', 'Эфиопия'),\n" +
                    "('FK', 'Фолклендские (Мальвинские) острова'),\n" +
                    "('FO', 'Фарерские острова'),\n" +
                    "('FJ', 'Фиджи'),\n" +
                    "('FI', 'Финляндия'),\n" +
                    "('FR', 'Франция'),\n" +
                    "('GF', 'Французская Гвиана'),\n" +
                    "('PF', 'Французская Полинезия'),\n" +
                    "('TF', 'Южные Французские Территории'),\n" +
                    "('GA', 'Габон'),\n" +
                    "('GM', 'Гамбия'),\n" +
                    "('GE', 'Грузия'),\n" +
                    "('DE', 'Германия'),\n" +
                    "('GH', 'Гана'),\n" +
                    "('GI', 'Гибралтар'),\n" +
                    "('GR', 'Греция'),\n" +
                    "('GL', 'Гренландия'),\n" +
                    "('GD', 'Гренада'),\n" +
                    "('GP', 'Гваделупа'),\n" +
                    "('GU', 'Гуам'),\n" +
                    "('GT', 'Гватемала'),\n" +
                    "('GG', 'Гернси'),\n" +
                    "('GN', 'Гвинея'),\n" +
                    "('GW', 'Гвинея-Бисау'),\n" +
                    "('GY', 'Гайана'),\n" +
                    "('HT', 'Гаити'),\n" +
                    "('HM', 'Остров Херд и острова Макдональдс'),\n" +
                    "('VA', 'Святой Престол (город-государство Ватикан)'),\n" +
                    "('HN', 'Гондурас'),\n" +
                    "('HK', 'Гонконг'),\n" +
                    "('HU', 'Венгрия'),\n" +
                    "('IS', 'Исландия'),\n" +
                    "('IN', 'Индия'),\n" +
                    "('ID', 'Индонезия'),\n" +
                    "('IR', 'Иран, Исламская Республика'),\n" +
                    "('IQ', 'Ирак'),\n" +
                    "('IE', 'Ирландия'),\n" +
                    "('IM', 'Остров Мэн'),\n" +
                    "('IL', 'Израиль'),\n" +
                    "('IT', 'Италия'),\n" +
                    "('JM', 'Ямайка'),\n" +
                    "('JP', 'Япония'),\n" +
                    "('JE', 'Джерси'),\n" +
                    "('JO', 'Иордания'),\n" +
                    "('KZ', 'Казахстан'),\n" +
                    "('KE', 'Кения'),\n" +
                    "('KI', 'Кирибати'),\n" +
                    "('KR', 'Корея'),\n" +
                    "('KP', 'Северная Корея'),\n" +
                    "('KW', 'Кувейт'),\n" +
                    "('KG', 'Кыргызстан'),\n" +
                    "('LA', 'Лаосская Народно-Демократическая Республика'),\n" +
                    "('LV', 'Латвия'),\n" +
                    "('LB', 'Ливан'),\n" +
                    "('LS', 'Лесото'),\n" +
                    "('LR', 'Либерия'),\n" +
                    "('LY', 'Ливийская арабская джамахирия'),\n" +
                    "('LI', 'Лихтенштейн'),\n" +
                    "('LT', 'Литва'),\n" +
                    "('LU', 'Люксембург'),\n" +
                    "('MO', 'Макао'),\n" +
                    "('MK', 'Македония'),\n" +
                    "('MG', 'Мадагаскар'),\n" +
                    "('MW', 'Малави'),\n" +
                    "('MY', 'Малайзия'),\n" +
                    "('MV', 'Мальдивы'),\n" +
                    "('ML', 'Мали'),\n" +
                    "('MT', 'Мальта'),\n" +
                    "('MH', 'Маршалловы острова'),\n" +
                    "('MQ', 'Мартиника'),\n" +
                    "('MR', 'Мавритания'),\n" +
                    "('MU', 'Маврикий'),\n" +
                    "('YT', 'Майотта'),\n" +
                    "('MX', 'Мексика'),\n" +
                    "('FM', 'Микронезия, Федеративные Штаты'),\n" +
                    "('MD', 'Молдова'),\n" +
                    "('MC', 'Монако'),\n" +
                    "('MN', 'Монголия'),\n" +
                    "('ME', 'Черногория'),\n" +
                    "('MS', 'Монтсеррат'),\n" +
                    "('MA', 'Марокко'),\n" +
                    "('MZ', 'Мозамбик'),\n" +
                    "('MM', 'Мьянма'),\n" +
                    "('NA', 'Намибия'),\n" +
                    "('NR', 'Науру'),\n" +
                    "('NP', 'Непал'),\n" +
                    "('NL', 'Нидерланды'),\n" +
                    "('AN', 'Нидерландские Антильские острова'),\n" +
                    "('NC', 'Новая Каледония'),\n" +
                    "('NZ', 'Новая Зеландия'),\n" +
                    "('NI', 'Никарагуа'),\n" +
                    "('NE', 'Нигер'),\n" +
                    "('NG', 'Нигерия'),\n" +
                    "('NU', 'Ниуэ'),\n" +
                    "('NF', 'Остров Норфолк'),\n" +
                    "('MP', 'Северные Марианские острова'),\n" +
                    "('NO', 'Норвегия'),\n" +
                    "('OM', 'Оман'),\n" +
                    "('PK', 'Пакистан'),\n" +
                    "('PW', 'Палау'),\n" +
                    "('PS', 'Палестинская территория, оккупированная'),\n" +
                    "('PA', 'Панама'),\n" +
                    "('PG', 'Папуа - Новая Гвинея'),\n" +
                    "('PY', 'Парагвай'),\n" +
                    "('PE', 'Перу'),\n" +
                    "('PH', 'Филиппины'),\n" +
                    "('PN', 'Питкэрн'),\n" +
                    "('PL', 'Польша'),\n" +
                    "('PT', 'Португалия'),\n" +
                    "('PR', 'Пуэрто-Рико'),\n" +
                    "('QA', 'Катар'),\n" +
                    "('RE', 'Реюньюн'),\n" +
                    "('RO', 'Румыния'),\n" +
                    "('RU', 'Российская Империя'),\n" +
                    "('RW', 'Руанда'),\n" +
                    "('BL', 'Сен-Бартельми'),\n" +
                    "('SH', 'Святая Елена'),\n" +
                    "('KN', 'Сент-Китс и Невис'),\n" +
                    "('LC', 'Санкт-Люсия'),\n" +
                    "('MF', 'Сен-Мартен'),\n" +
                    "('PM', 'Сен-Пьер и Микелон'),\n" +
                    "('VC', 'Сент-Винсент и Гренадины'),\n" +
                    "('WS', 'Самоа'),\n" +
                    "('SM', 'Сан-Марино'),\n" +
                    "('ST', 'Сан-Томе и Принсипи'),\n" +
                    "('SA', 'Саудовская Аравия'),\n" +
                    "('SN', 'Сенегал'),\n" +
                    "('RS', 'Сербия'),\n" +
                    "('SC', 'Сейшелы'),\n" +
                    "('SL', 'Сьерра-Леоне'),\n" +
                    "('SG', 'Сингапур'),\n" +
                    "('SK', 'Словакия'),\n" +
                    "('SI', 'Словения'),\n" +
                    "('SB', 'Соломоновы острова'),\n" +
                    "('SO', 'Сомали'),\n" +
                    "('ZA', 'Южная Африка'),\n" +
                    "('GS', 'Южная Георгия и Сэндвич-Айленд.'),\n" +
                    "('ES', 'Испания'),\n" +
                    "('LK', 'Шри-Ланка'),\n" +
                    "('SD', 'Судан'),\n" +
                    "('SR', 'Суринам'),\n" +
                    "('SJ', 'Шпицберген и Ян-Майен'),\n" +
                    "('SZ', 'Свазиленд'),\n" +
                    "('SE', 'Швеция'),\n" +
                    "('CH', 'Швейцария'),\n" +
                    "('SY', 'Сирийская Арабская Республика'),\n" +
                    "('TW', 'Тайвань'),\n" +
                    "('TJ', 'Таджикистан'),\n" +
                    "('TZ', 'Танзания'),\n" +
                    "('TH', 'Таиланд'),\n" +
                    "('TL', 'Тимор-Лешти'),\n" +
                    "('TG', 'Идти'),\n" +
                    "('TK', 'Токелау'),\n" +
                    "('TO', 'Тонга'),\n" +
                    "('TT', 'Тринидад и Тобаго'),\n" +
                    "('TN', 'Тунис'),\n" +
                    "('TR', 'Турция'),\n" +
                    "('TM', 'Туркменистан'),\n" +
                    "('TC', 'Острова Теркс и Кайкос'),\n" +
                    "('TV', 'Тувалу'),\n" +
                    "('UG', 'Уганда'),\n" +
                    "('UA', 'Украина'),\n" +
                    "('AE', 'Объединенные Арабские Эмираты'),\n" +
                    "('GB', 'Великобритания'),\n" +
                    "('US', 'Соединенные Штаты Америки'),\n" +
                    "('UM', 'Отдалённые острова США'),\n" +
                    "('UY', 'Уругвай'),\n" +
                    "('UZ', 'Узбекистан'),\n" +
                    "('VU', 'Вануату'),\n" +
                    "('VE', 'Венесуэла'),\n" +
                    "('VN', 'Вьетнам'),\n" +
                    "('VG', 'Виргинские острова, Британские'),\n" +
                    "('VI', 'Виргинские острова, США'),\n" +
                    "('WF', 'Уоллис и Футуна'),\n" +
                    "('EH', 'Западная Сахара'),\n" +
                    "('YE', 'Йемен'),\n" +
                    "('ZM', 'Замбия'),\n" +
                    "('ZW', 'Зимбабве');";

    private static final String SQL_DELETE_ISO_TABLE =
            "DROP TABLE IF EXISTS " + HolidayDBContract.ISOCodeEntry.TABLE_NAME + ";";

    private static final String SQL_TRUNCATE_ISO_TABLE =
            "DELETE FROM " + HolidayDBContract.ISOCodeEntry.TABLE_NAME + ";";

    private static final String SQL_DELETE_HOLIDAY_TABLE =
            "DROP TABLE IF EXISTS " + HolidayDBContract.HolidayEntry.TABLE_NAME + ";";

    private static final String SQL_TRUNCATE_HOLIDAY_TABLE =
            "DELETE FROM " + HolidayDBContract.HolidayEntry.TABLE_NAME + ";";

    public SqliteDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_IDO_CODES_TABLE);
        db.execSQL(SQL_INSERT_ISO_VALUES);
        db.execSQL(SQL_CREATE_HOLIDAY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
