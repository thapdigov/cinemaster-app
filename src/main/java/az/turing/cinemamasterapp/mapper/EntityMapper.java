package az.turing.cinemamasterapp.mapper;

public interface EntityMapper<E, D> {

    E toEnt(D d);

    D toDto(E e);
}
