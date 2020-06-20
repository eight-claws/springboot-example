

-- ----------------------------
-- Table structure for device
-- ----------------------------
DROP TABLE IF EXISTS "public"."device";
CREATE TABLE "public"."device" (
  "id" varchar(32) COLLATE "pg_catalog"."default" NOT NULL,
  "name" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "region_code" varchar(32) COLLATE "pg_catalog"."default" NOT NULL,
  "region_path" varchar(1000) COLLATE "pg_catalog"."default",
  "treaty_type" varchar(60) COLLATE "pg_catalog"."default",
  "ip" varchar(20) COLLATE "pg_catalog"."default",
  "port" int4,
  "delete_status" int4 DEFAULT 0,
  "create_time" timestamp(6),
  "update_time" timestamp(6)
)
;
COMMENT ON COLUMN "public"."device"."id" IS '主键，使用32位UUID';
COMMENT ON COLUMN "public"."device"."name" IS '设备名称';
COMMENT ON COLUMN "public"."device"."region_code" IS '所属区域code';
COMMENT ON COLUMN "public"."device"."region_path" IS '所属区域path';
COMMENT ON COLUMN "public"."device"."treaty_type" IS '设备接入协议';
COMMENT ON COLUMN "public"."device"."ip" IS '设备ip';
COMMENT ON COLUMN "public"."device"."port" IS '设备端口号';
COMMENT ON COLUMN "public"."device"."delete_status" IS '删除状态。1-删除，0-未删除（默认值）';
COMMENT ON COLUMN "public"."device"."create_time" IS '创建时间，不带时区';
COMMENT ON COLUMN "public"."device"."update_time" IS '更新时间，不带时区';
COMMENT ON TABLE "public"."device" IS '设备表，同步到本地';

-- ----------------------------
-- Table structure for region
-- ----------------------------
DROP TABLE IF EXISTS "public"."region";
CREATE TABLE "public"."region" (
  "id" varchar(32) COLLATE "pg_catalog"."default" NOT NULL,
  "name" varchar(200) COLLATE "pg_catalog"."default" NOT NULL,
  "path" varchar(1000) COLLATE "pg_catalog"."default" NOT NULL,
  "leaf" int4,
  "delete_status" int4 NOT NULL DEFAULT 0,
  "code" varchar(32) COLLATE "pg_catalog"."default",
  "parent_code" varchar(32) COLLATE "pg_catalog"."default",
  "type" int4,
  "sore" int4,
  "create_time" timestamp(6),
  "update_time" timestamp(6)
)
;
COMMENT ON COLUMN "public"."region"."id" IS '主键，32位uuid';
COMMENT ON COLUMN "public"."region"."name" IS '区域名称';
COMMENT ON COLUMN "public"."region"."path" IS '区域path';
COMMENT ON COLUMN "public"."region"."leaf" IS '是否是叶子节点。0-叶子节点，1-非叶子节点';
COMMENT ON COLUMN "public"."region"."delete_status" IS '删除状态。1-删除，0未删除（默认值）';
COMMENT ON COLUMN "public"."region"."code" IS '区域code';
COMMENT ON COLUMN "public"."region"."parent_code" IS '父节点的code';
COMMENT ON COLUMN "public"."region"."type" IS '区域类型。1-普通，2-工地';
COMMENT ON COLUMN "public"."region"."sort" IS '排序字段，从1开始';
COMMENT ON COLUMN "public"."region"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."region"."update_time" IS '修改时间';
COMMENT ON TABLE "public"."region" IS '区域表';

-- ----------------------------
-- Primary Key structure for table device
-- ----------------------------
ALTER TABLE "public"."device" ADD CONSTRAINT "device_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table region
-- ----------------------------
ALTER TABLE "public"."region" ADD CONSTRAINT "region_pkey" PRIMARY KEY ("id");
