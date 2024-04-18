package ru.fildv.datastoregrpcmicroservice.web.mapper;

public interface Mappable<E, D> {
    D toDto(E entry);
}
