CREATE TABLE T_USER_ACCOUNT
(
    ID                        BIGSERIAL PRIMARY KEY,
    EMAIL                     VARCHAR(64),
    PASSWORD                  VARCHAR(128),
    NICKNAME                  VARCHAR(128),
    LAST_LOGIN                TIMESTAMPTZ,
    LAST_IP                   VARCHAR(32),

    CREATE_TIME               TIMESTAMP NOT NULL DEFAULT NOW(),
    UPDATE_TIME               TIMESTAMP NOT NULL DEFAULT NOW(),

    UNIQUE (EMAIL)

);

COMMENT ON TABLE T_USER_ACCOUNT IS '用户表';

insert into T_USER_ACCOUNT (EMAIL, PASSWORD, NICKNAME)
value ('test@163.com', '123456', '小度音箱')


CREATE TABLE T_OAUTH_CODE
(
    ID             BIGSERIAL    PRIMARY KEY,
    CODE           VARCHAR(255)  NOT NULL,
    AUTHENTICATION text       NOT NULL,
    CREATE_TIME timestamp without time zone NOT NULL DEFAULT now(),
    UNIQUE (CODE)
);

COMMENT ON COLUMN T_OAUTH_CODE.CODE IS 'auth-code表';
COMMENT ON COLUMN T_OAUTH_CODE.AUTHENTICATION IS '身份信息';
COMMENT ON COLUMN T_OAUTH_CODE.CREATE_TIME IS '认证信息添加时间';


create table oauth_client_details (
  client_id VARCHAR(256) PRIMARY KEY,
  resource_ids VARCHAR(256),
  client_secret VARCHAR(256),
  scope VARCHAR(256),
  authorized_grant_types VARCHAR(256),
  web_server_redirect_uri VARCHAR(256),
  authorities VARCHAR(256),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information TEXT,
  autoapprove VARCHAR(256) DEFAULT 'true',
  CREATE_TIME TIMESTAMPTZ NOT NULL DEFAULT NOW(),
  UPDATE_TIME TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

insert into oauth_client_details
(client_id, resource_ids, client_secret,  scope, authorized_grant_types,
authorities, web_server_redirect_uri)
values ('client_2', 'openapi', '123456', 'select',
 'password,authorization_code,refresh_token', 'ROLE_CLIENT,ROLE_TRUSTED_CLIENT',
 60, 'http://www.baidu.com');



