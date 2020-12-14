SET SCHEMA PERSONALOG;

    create table ENTRY_LOG (
       id_entry integer identity primary key,
        dt_entry timestamp,
        ds_entry varchar(255),
        dt_save timestamp,
        mood_id integer,
        primary key (id_entry)
    );

    create table MOOD (
       id_mood integer identity primary key,
        vl_hex_color varchar(255),
        ds_mood varchar(255),
        dt_save timestamp,
        vl_presentation_order integer,
        primary key (id_mood)
    );

    alter table ENTRY_LOG
       add constraint fk_entrylog_mood
       foreign key (mood_id)
       references mood;

insert
    into
        personalog.mood
        (vl_hex_color, ds_mood, dt_save, vl_presentation_order)
    values
        ('000000', 'SAD', CURRENT_TIMESTAMP , 0),
        ('000001', 'HAPPY', CURRENT_TIMESTAMP , 1),
        ('000002', 'WORRIED', CURRENT_TIMESTAMP , 2),
        ('000003', 'SCARY', CURRENT_TIMESTAMP , 3),
        ('000004', 'AMAZED', CURRENT_TIMESTAMP , 4);