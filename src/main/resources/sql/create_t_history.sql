-- DROP TABLE public.t_history;
-- DROP SEQUENCE public.t_history_seq;

CREATE SEQUENCE public.t_history_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;

CREATE TABLE public.t_history (
	history_idx int8 DEFAULT nextval('t_history_seq'::regclass) NOT NULL,
	ac_idx int8 NOT NULL,
	user_idx int8 NOT NULL,
	act_idx int8 NOT NULL,
	history_act_either varchar(1) NULL,
	history_ac_money_type varchar(10) NULL,
	history_name varchar(50) NULL,
	history_amounts int4 NULL,
	history_memo text NULL,
	history_create_at timestamp NULL,
	history_update_at timestamp NULL,
	CONSTRAINT pk_t_history PRIMARY KEY (history_idx)
);
ALTER TABLE public.t_history ADD CONSTRAINT t_history_t_accounting_types_fk FOREIGN KEY (act_idx) REFERENCES public.t_accounting_types(act_idx);
ALTER TABLE public.t_history ADD CONSTRAINT t_history_t_accounts_fk FOREIGN KEY (ac_idx) REFERENCES public.t_accounts(ac_idx);
ALTER TABLE public.t_history ADD CONSTRAINT t_history_t_users_fk FOREIGN KEY (user_idx) REFERENCES public.t_users(user_idx);