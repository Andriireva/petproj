package com.example.petproject.repositories;

import java.util.List;

public interface Paginated<T> {
  List<T> getPaginatedData(int pageNumber, int pageSize) throws Exception;
}
