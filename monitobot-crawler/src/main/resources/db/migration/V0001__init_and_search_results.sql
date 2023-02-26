create table tracks (
    id bigint primary key,
    public_id varchar(15) not null unique ,
    created_on_utc timestamp without time zone not null,
    last_run_on_utc timestamp without time zone null,
    search_criteria jsonb not null
);
create sequence sq_tracks_id start with 1 increment by 1;
create index ix_tracks_public_id on tracks(public_id);

create table search_results (
    id bigint primary key,
    track_id bigint not null references tracks(id),
    search_engine varchar(15) not null,
    created_on_utc timestamp without time zone not null,
    status_code smallint,
    raw_data text null
);
create sequence sq_search_results_id start with 1 increment by 1;
create index ix_search_results_tracks_id on search_results(track_id);