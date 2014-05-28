--Tabletype 1: table, 2: structure.
create table tables(tablename varchar(20), tabletype int, comment varchar(30));
create table tablecols(tablename varchar(20), colname varchar(20), fieldname varchar(25), 
                                                                   fieldtype int, primary key (tablename, colname));
--Field
create table fields(fieldname varchar(25), fieldtype int, fieldlength int, fielddecimal int, 
                                          displaylength int, displaydecimal int, fieldimp varchar(25)
   , primary key (fieldname));  
create table fieldtypes(fieldtype int, 
                        islengthvar boolean,
                        isdecimalvar boolean,
                        fieldtypeimp varchar(25), 
                        priamry key (fieldtype));
insert into fieldtypes values ('1', false, false, 'Integer'),
                              ('2', false, false, 'Bollean'),
                              ('3', true, false,  'String'),
                              ('4', false, false, 'Bigint'),
                              ('5', false, false, 'Float'),
                              ('6', false, false, 'Double'),
                              ('7', false, false, 'BigDecimal'),
                              ('8', false, false, 'Stream');
                              ('9', true, true, 'Pack');
/* 
    public static final int TYPE_INT = 1;
    public static final int TYPE_BOOL = 2;
    public static final int TYPE_STRING = 3;
    public static final int TYPE_BIGINT = 4;
    public static final int TYPE_FLOAT = 5;
    public static final int TYPE_DOUBLE = 6;
    public static final int TYPE_BIGDECIMAL = 7;
    public static final int TYPE_BLOB = 8;
    public static final int TYPE_URL = 9;
    public static final int TYPE_FILENAME = 10;
    public static final int TYPE_HOSTNAME = 11;
    public static final int TYPE_IPV4 = 12;
    public static final int TYPE_IPV6 = 13;
    public static final int TYPE_PACK = 14;
*/
