CREATE SCHEMA IF NOT EXISTS survey_admin;
CREATE TABLE IF NOT EXISTS survey
(
    id    serial primary key,
    title varchar not null unique
);

CREATE TABLE IF NOT EXISTS question
(
    id        serial primary key,
    text      varchar not null,
    survey_id integer references survey (id)

);

CREATE TABLE IF NOT EXISTS option
(
    id           serial primary key,
    option_order int4    not null,
    text         varchar not null,
    question_id  integer references question (id)
);