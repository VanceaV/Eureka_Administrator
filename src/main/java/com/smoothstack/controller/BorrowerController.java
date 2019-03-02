package com.smoothstack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smoothstack.entity.Borrower;
import com.smoothstack.service.BorrowerService;

@RestController
@RequestMapping("/lms/administrator")
public class BorrowerController {

	@Autowired
	private BorrowerService borrowerRepository;

	@GetMapping("/borrowers")
	public ResponseEntity<List<Borrower>> getAllBorrowers() {
		List<Borrower> list = borrowerRepository.getAll();
		return new ResponseEntity<List<Borrower>>(list, HttpStatus.OK);
	}

	@PostMapping("/borrowers/borrower")
	public ResponseEntity<Borrower> addBorrower(@RequestBody Borrower borrower) {
		borrowerRepository.create(borrower);
		return new ResponseEntity<Borrower>(HttpStatus.CREATED);
	}

	@PutMapping("/borrowers/borrower")
	public ResponseEntity<Borrower> updateBorrower(@RequestBody Borrower borrower) {
		borrowerRepository.update(borrower);

		try {
			borrowerRepository.update(borrower);
		} catch (Exception e) {
			return new ResponseEntity<Borrower>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<Borrower>(HttpStatus.OK);
	}

	@DeleteMapping("/borrowers/{borrowerId}")
	public ResponseEntity<Borrower> deleteBorrower(@PathVariable long borrowerId) {

		try {
			borrowerRepository.delete(borrowerId);
		} catch (Exception e) {
			return new ResponseEntity<Borrower>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Borrower>(HttpStatus.ACCEPTED);
	}
}
