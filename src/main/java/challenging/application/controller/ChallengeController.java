package challenging.application.controller;

import challenging.application.auth.annotation.LoginMember;
import challenging.application.auth.domain.Member;
import challenging.application.dto.request.ChallengeRequestDTO;
import challenging.application.dto.response.ChallengeResponseDTO;
import challenging.application.service.ChallengeService;
import java.util.List;
import java.util.Map;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/challenges")
public class ChallengeController {

  private final ChallengeService challengeService;

  public ChallengeController(ChallengeService challengeService) {
    this.challengeService = challengeService;
  }

  // 챌린지 단건 조회
  @GetMapping("/{challengeId}")
  public ResponseEntity<ChallengeResponseDTO> getChallenge(
      @PathVariable Long challengeId,
      @RequestBody Map<String, String> requestBody) {

    ChallengeResponseDTO response = challengeService.getChallengeByIdAndDate(challengeId,
        requestBody.get("date"));

    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  // 챌린지 카테고리 조회
  @GetMapping("/{categoryId}")
  public ResponseEntity<List<ChallengeResponseDTO>> getChallengesByCategory(
      @PathVariable int categoryId,
      @RequestBody Map<String, String> requestBody) {

    List<ChallengeResponseDTO> responses = challengeService.getChallengesByCategoryAndDate(
        categoryId, requestBody.get("date"));

    return ResponseEntity.status(HttpStatus.CREATED).body(responses);
  }

  // 챌린지 생성
  @PostMapping
  public ResponseEntity<Long> createChallenge(
      @RequestBody ChallengeRequestDTO challengeRequestDTO) {

    Long challengeId = challengeService.createChallenge(challengeRequestDTO);

    return ResponseEntity.status(HttpStatus.CREATED).body(challengeId);
  }

  // 챌린지 삭제
  @DeleteMapping("{challengeId}")
  public ResponseEntity<Long> deleteChallenge(@PathVariable Long challengeId) {
    challengeService.deleteChallenge(challengeId);
    return ResponseEntity.status(HttpStatus.OK).body(challengeId);
  }

  // 챌린지 예약
  @PostMapping("/reservation/{challengeId}")
  public ResponseEntity<?> reserveChallenge(@PathVariable Long challengeId,
      @LoginMember Member loginMember) {
    challengeService.reserveChallenge(challengeId, loginMember);

    return ResponseEntity.status(HttpStatus.OK).body(
        Map.of("challenge_id", challengeId, "user_id", loginMember.getId())
    );
  }

}