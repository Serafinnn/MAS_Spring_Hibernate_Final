CREATE SEQUENCE country_gen START WITH 1 INCREMENT BY 1
GO

CREATE SEQUENCE department_gen START WITH 1 INCREMENT BY 1
GO

CREATE SEQUENCE framework_gen START WITH 1 INCREMENT BY 1
GO

CREATE SEQUENCE init_gen START WITH 1 INCREMENT BY 1
GO

CREATE SEQUENCE person_gen START WITH 1 INCREMENT BY 1
GO

CREATE SEQUENCE type_ofseq START WITH 1 INCREMENT BY 1
GO

CREATE TABLE charity_project
(
    id          int NOT NULL,
    name_of_org varchar(255),
    CONSTRAINT pk_charityproject PRIMARY KEY (id)
)
GO

CREATE TABLE client
(
    id        int NOT NULL,
    net_worth int NOT NULL,
    CONSTRAINT pk_client PRIMARY KEY (id)
)
GO

CREATE TABLE client_employee
(
    id int NOT NULL,
    CONSTRAINT pk_clientemployee PRIMARY KEY (id)
)
GO

CREATE TABLE code_project
(
    id         int NOT NULL,
    start_date date,
    completed  bit NOT NULL,
    CONSTRAINT pk_codeproject PRIMARY KEY (id)
)
GO

CREATE TABLE country
(
    id                        int       NOT NULL,
    full_name                 varchar(255),
    short_name                varchar(255),
    surface_in_meters_squared float(53) NOT NULL,
    CONSTRAINT pk_country PRIMARY KEY (id)
)
GO

CREATE TABLE country_department_qualification
(
    country_id                   int          NOT NULL,
    department_qualification_id  int          NOT NULL,
    department_qualification_key varchar(255) NOT NULL,
    CONSTRAINT pk_country_departmentqualification PRIMARY KEY (country_id, department_qualification_key)
)
GO

CREATE TABLE department
(
    id         int NOT NULL,
    name       varchar(255),
    location   varchar(255),
    country_id int,
    CONSTRAINT pk_department PRIMARY KEY (id)
)
GO

CREATE TABLE string
(
    id            int       NOT NULL,
    salary        float(53) NOT NULL,
    emp_date      date,
    department_id int,
    CONSTRAINT pk_employee PRIMARY KEY (id)
)
GO

CREATE TABLE employee_initiative
(
    employee_id   int NOT NULL,
    initiative_id int NOT NULL,
    assigned      date,
    CONSTRAINT pk_employee_initiative PRIMARY KEY (employee_id, initiative_id)
)
GO

CREATE TABLE employee_person_traits
(
    employee_id   int NOT NULL,
    person_traits int
)
GO

CREATE TABLE framework
(
    id      int NOT NULL,
    name    varchar(255),
    company varchar(255),
    CONSTRAINT pk_framework PRIMARY KEY (id)
)
GO

CREATE TABLE free_framework
(
    id       int NOT NULL,
    why_free varchar(255),
    CONSTRAINT pk_freeframework PRIMARY KEY (id)
)
GO

CREATE TABLE initiative
(
    id                    int NOT NULL,
    name                  varchar(255),
    description           varchar(255),
    type_id               int,
    type_of_initiative_id bigint,
    CONSTRAINT pk_initiative PRIMARY KEY (id)
)
GO

CREATE TABLE paid_framework
(
    id            int NOT NULL,
    monthly_price int NOT NULL,
    CONSTRAINT pk_paidframework PRIMARY KEY (id)
)
GO

CREATE TABLE person
(
    id         int NOT NULL,
    first_name varchar(255),
    last_name  varchar(255),
    birth_date date,
    CONSTRAINT pk_person PRIMARY KEY (id)
)
GO

CREATE TABLE project
(
    id          bigint NOT NULL,
    name        varchar(255),
    description varchar(255),
    CONSTRAINT pk_project PRIMARY KEY (id)
)
GO

CREATE TABLE project_initiatives
(
    type_of_initiative_id bigint NOT NULL,
    initiatives_id        int    NOT NULL
)
GO

ALTER TABLE country_department_qualification
    ADD CONSTRAINT uc_country_department_qualification_departmentqualification UNIQUE (department_qualification_id)
GO

ALTER TABLE project_initiatives
    ADD CONSTRAINT uc_project_initiatives_initiatives UNIQUE (initiatives_id)
GO

ALTER TABLE charity_project
    ADD CONSTRAINT FK_CHARITYPROJECT_ON_ID FOREIGN KEY (id) REFERENCES initiative (id)
GO

ALTER TABLE client_employee
    ADD CONSTRAINT FK_CLIENTEMPLOYEE_ON_ID FOREIGN KEY (id) REFERENCES string (id)
GO

ALTER TABLE client
    ADD CONSTRAINT FK_CLIENT_ON_ID FOREIGN KEY (id) REFERENCES person (id)
GO

ALTER TABLE code_project
    ADD CONSTRAINT FK_CODEPROJECT_ON_ID FOREIGN KEY (id) REFERENCES initiative (id)
GO

ALTER TABLE department
    ADD CONSTRAINT FK_DEPARTMENT_ON_COUNTRY FOREIGN KEY (country_id) REFERENCES country (id)
GO

ALTER TABLE employee_initiative
    ADD CONSTRAINT FK_EMPLOYEE_INITIATIVE_ON_EMPLOYEE FOREIGN KEY (employee_id) REFERENCES string (id)
GO

ALTER TABLE employee_initiative
    ADD CONSTRAINT FK_EMPLOYEE_INITIATIVE_ON_INITIATIVE FOREIGN KEY (initiative_id) REFERENCES initiative (id)
GO

ALTER TABLE string
    ADD CONSTRAINT FK_EMPLOYEE_ON_DEPARTMENT FOREIGN KEY (department_id) REFERENCES department (id)
GO

ALTER TABLE string
    ADD CONSTRAINT FK_EMPLOYEE_ON_ID FOREIGN KEY (id) REFERENCES person (id)
GO

ALTER TABLE free_framework
    ADD CONSTRAINT FK_FREEFRAMEWORK_ON_ID FOREIGN KEY (id) REFERENCES framework (id)
GO

ALTER TABLE initiative
    ADD CONSTRAINT FK_INITIATIVE_ON_TYPE FOREIGN KEY (type_id) REFERENCES framework (id)
GO

ALTER TABLE initiative
    ADD CONSTRAINT FK_INITIATIVE_ON_TYPEOFINITIATIVE FOREIGN KEY (type_of_initiative_id) REFERENCES project (id)
GO

ALTER TABLE paid_framework
    ADD CONSTRAINT FK_PAIDFRAMEWORK_ON_ID FOREIGN KEY (id) REFERENCES framework (id)
GO

ALTER TABLE country_department_qualification
    ADD CONSTRAINT fk_coudepqua_on_country FOREIGN KEY (country_id) REFERENCES country (id)
GO

ALTER TABLE country_department_qualification
    ADD CONSTRAINT fk_coudepqua_on_department FOREIGN KEY (department_qualification_id) REFERENCES department (id)
GO

ALTER TABLE employee_person_traits
    ADD CONSTRAINT fk_employee_persontraits_on_employee FOREIGN KEY (employee_id) REFERENCES string (id)
GO

ALTER TABLE project_initiatives
    ADD CONSTRAINT fk_proini_on_initiative FOREIGN KEY (initiatives_id) REFERENCES initiative (id)
GO

ALTER TABLE project_initiatives
    ADD CONSTRAINT fk_proini_on_type_of_initiative FOREIGN KEY (type_of_initiative_id) REFERENCES project (id)
GO