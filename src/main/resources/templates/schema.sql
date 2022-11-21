create table if not exists assignment_sequence
(
    next_val bigint null
);

create table if not exists chore_sequence
(
    next_val bigint null
);

create table if not exists message_sequence
(
    next_val bigint null
);

create table if not exists receipt_sequence
(
    next_val bigint null
);

create table if not exists user
(
    id            bigint       not null
        primary key,
    api_key       varchar(255) null,
    app_user_role varchar(255) null,
    dob           varchar(255) null,
    email         varchar(255) null,
    enabled       bit          null,
    first_name    varchar(255) null,
    last_name     varchar(255) null,
    locked        bit          null,
    password      varchar(255) null,
    group_id      bigint       null
);

create table if not exists message
(
    id            bigint           not null
        primary key,
    body          varchar(255)     null,
    has_been_read bit              null,
    read_at       datetime(6)      null,
    sent_at       datetime(6)      null,
    subject       varchar(255)     null,
    user_id_from  bigint           not null,
    user_id_to    bigint           not null,
    deleted       bit default b'0' null,
    constraint FKrivvq3p0w47c6fetw0lbs1vbb
        foreign key (user_id_from) references user (id),
    constraint FKs1f3xg9pb77b0d66npom9t1nu
        foreign key (user_id_to) references user (id)
);

create table if not exists user_group
(
    id      bigint      not null
        primary key,
    created datetime(6) null,
    user_id bigint      null,
    constraint FK1c1dsw3q36679vaiqwvtv36a6
        foreign key (user_id) references user (id)
            on update cascade on delete cascade
);

create table if not exists chore
(
    id          bigint           not null
        primary key,
    multiplier  int              null,
    name        varchar(255)     null,
    group_id    bigint           null,
    enabled     bit default b'1' not null,
    description varchar(255)     null,
    constraint FKm2anc2dld6hovh0l18v9vpke7
        foreign key (group_id) references user_group (id)
);

create table if not exists assignment
(
    id       bigint               not null
        primary key,
    chore_id bigint               not null,
    user_id  bigint               not null,
    active   tinyint default 1    null,
    start    datetime(6)          null,
    end      datetime(6)          null,
    done     bit     default b'0' null,
    approved bit     default b'0' null,
    constraint FK52yu4o12wefoh3q5na617u86v
        foreign key (user_id) references user (id),
    constraint FKecr64sk6rnqx5r74inshxxoi5
        foreign key (chore_id) references chore (id)
);

create table if not exists receipt
(
    id            bigint      not null
        primary key,
    confirmed     bit         not null,
    paid          bit         not null,
    timestamp     datetime(6) null,
    assignment_id bigint      not null,
    constraint FK4hu7ouekowna3ufpwpnikh0m1
        foreign key (assignment_id) references assignment (id)
);

create table if not exists user_group_sequence
(
    next_val bigint null
);

create table if not exists user_sequence
(
    next_val bigint null
);

create or replace view dashboard as
select row_number() OVER (ORDER BY `chores1`.`user`.`first_name` desc,`chores1`.`chore`.`multiplier` ) AS `id`,
       `chores1`.`user`.`first_name`                                                                   AS `first_name`,
       `chores1`.`user`.`id`                                                                           AS `user_id`,
       `chores1`.`user`.`group_id`                                                                     AS `group_id`,
       `chores1`.`assignment`.`id`                                                                     AS `assignment_id`,
       `chores1`.`chore`.`name`                                                                        AS `name`,
       `chores1`.`chore`.`description`                                                                 AS `description`,
       `chores1`.`chore`.`multiplier`                                                                  AS `multiplier`,
       `chores1`.`assignment`.`done`                                                                   AS `done`
from ((`chores1`.`assignment` join `chores1`.`user` on ((`chores1`.`assignment`.`user_id` = `chores1`.`user`.`id`)))
         join `chores1`.`chore` on ((`chores1`.`assignment`.`chore_id` = `chores1`.`chore`.`id`)))
where ((`chores1`.`assignment`.`active` = 1) and (`chores1`.`user`.`enabled` = 1) and
       (`chores1`.`chore`.`enabled` = 1) and
       (now() between `chores1`.`assignment`.`start` and `chores1`.`assignment`.`end`))
order by `chores1`.`user`.`first_name` desc, `chores1`.`chore`.`multiplier`;

create or replace view last_weeks_dashboard as
select row_number() OVER (ORDER BY `chores1`.`user`.`first_name` desc,`chores1`.`chore`.`multiplier` ) AS `id`,
       `chores1`.`user`.`first_name`                                                                   AS `first_name`,
       `chores1`.`user`.`id`                                                                           AS `user_id`,
       `chores1`.`user`.`group_id`                                                                     AS `group_id`,
       `chores1`.`assignment`.`id`                                                                     AS `assignment_id`,
       `chores1`.`chore`.`name`                                                                        AS `name`,
       `chores1`.`chore`.`description`                                                                 AS `description`,
       `chores1`.`chore`.`multiplier`                                                                  AS `multiplier`,
       `chores1`.`assignment`.`done`                                                                   AS `done`
from ((`chores1`.`assignment` join `chores1`.`user` on ((`chores1`.`assignment`.`user_id` = `chores1`.`user`.`id`)))
         join `chores1`.`chore` on ((`chores1`.`assignment`.`chore_id` = `chores1`.`chore`.`id`)))
where ((`chores1`.`assignment`.`active` = 1) and (`chores1`.`user`.`enabled` = 1) and
       (`chores1`.`chore`.`enabled` = 1) and
       ((now() - interval 1 week) between `chores1`.`assignment`.`start` and `chores1`.`assignment`.`end`))
order by `chores1`.`user`.`first_name` desc, `chores1`.`chore`.`multiplier`;

