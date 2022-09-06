package com.kosta.finalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.finalproject.model.FindCarBoard;

public interface FindCarBoardRepository  extends JpaRepository<FindCarBoard, Integer> {



	FindCarBoard findByboardnum(Integer boardnum);
	FindCarBoard deleteByboardnum(Long boardnum);
}
