SET SCHEMA PERSONALOG;

    create table ENTRY_LOG (
        id_entry bigint identity primary key,
        fk_mood bigint,
        fk_user bigint,
        dt_entry timestamp,
        ds_entry varchar(255),
        dt_creation timestamp,
        primary key (id_entry)
    );

    create table MOOD (
        id_mood bigint identity primary key,
        vl_hex_color varchar(255),
        ds_mood varchar(255),
        dt_creation timestamp,
        vl_presentation_order integer,
        primary key (id_mood)
    );

    create table USER (
        id_user bigint identity primary key,
        nm_user_first varchar(255),
        nm_user_last varchar(255),
        password varchar(255),
        email varchar(255),
        ds_roles varchar(255),
        dt_creation timestamp,
        fl_enabled boolean default false,
        primary key (id_user)
    );

    create table VERIFICATION_TOKEN (
        id_verif_token bigint identity primary key,
        ds_token varchar(255),
        fk_user bigint,
        dt_creation timestamp,
        dt_expiration timestamp,
        primary key (id_verif_token),
        constraint fk_token_user foreign key (fk_user) references USER
    );

    alter table ENTRY_LOG
       add constraint fk_entrylog_mood
       foreign key (fk_mood)
       references mood;

    alter table ENTRY_LOG
       add constraint fk_entrylog_user
       foreign key (fk_user)
       references USER;

insert
    into
        personalog.user
        (nm_user_first, nm_user_last, email, password, ds_roles)
    values
        ('Admin', 'Admin', 'admin@mail.com', '$2a$10$PWw.VGdOiYLDFose68Vht.t9BEy2dLcov8xCatz2dXNX.TPuoY48e', 'USER,ADMIN');

insert
    into
        personalog.mood
        (vl_hex_color, ds_mood, dt_creation, vl_presentation_order)
    values
        ('000000', 'SAD', CURRENT_TIMESTAMP , 0),
        ('000001', 'HAPPY', CURRENT_TIMESTAMP , 1),
        ('000002', 'WORRIED', CURRENT_TIMESTAMP , 2),
        ('000003', 'SCARY', CURRENT_TIMESTAMP , 3),
        ('000004', 'AMAZED', CURRENT_TIMESTAMP , 4);