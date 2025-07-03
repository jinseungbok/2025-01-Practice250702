<script setup>
import httpService from "@/services/HttpService";
import { reactive, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";

const router = useRouter();

const state = reactive({
  memo: {
    id: 0,
    title: "",
    content: "",
    createdAt: "",
  },
});

onMounted(() => {
  const passData = history.state.data;
  console.log("passData:", passData);
  if (passData) {
    //넘어온 데이터가 있다면
    state.memo = JSON.parse(passData); // JSON to Object
  }
});
// 수정만 detail.vue로
const procSubmit = async () => {
  const jsonBody = {
    title: state.memo.title,
    content: state.memo.content,
  };
  let data = null;
  let path = "/";
  if (state.memo.id) {
    path = `/memos/${state.memo.id}`;
    jsonBody.id = state.memo.id;
    data = await httpService.modify(jsonBody);
  } else {
    data = await httpService.save(jsonBody);
  }

  if (data.resultData === 1) {
    router.push({ path });
  } else {
    alert(data.resultMessage);
  }
};
</script>

<!-- 
    let data = null;
    if (state.memo.id) {
    // 수정
    jsonBody.id = state.memo.id;
    data = await httpService.modify(jsonBody);
  } else {
    // 등록
    data = await httpService.save(jsonBody);
  }
  if (data.resultData === 1) {
    // 등록/수정 성공
    // 홈화면으로 라우터 처리
    router.push({ path: '/' });
  } else {
    //등록 실패
    alert(data.resultMessage);
  }
  if (data.resultData === 1) {
    // 주소가 "/"으로 라우팅 처리 하고 싶다.
    router.push({ path: '/' });
  } else {
    alert(data.resultMessage);
  }
  -->

<template>
  <form class="detail" @submit.prevent="procSubmit">
    <div class="mb-3" v-if="state.memo.createdAt">
      등록일시: {{ state.memo.createdAt }}
    </div>
    <div class="mb-3">
      <label for="title" class="form-label">제목</label>
      <input
        type="text"
        id="title"
        class="form-control p-3"
        v-model="state.memo.title"
      />
    </div>
    <div class="mb-3">
      <label for="content" class="form-label">내용</label>
      <textarea
        id="content"
        class="form-control p-3"
        v-model="state.memo.content"
      ></textarea>
    </div>
    <button type="submit" class="btn btn-primary w-100 py-3">
      {{ state.memo.id > 0 ? "수정" : "저장" }}
    </button>
  </form>
</template>

<style scoped></style>
