<template>
  <li class="comment">
    <div class="vcard bio">
      <img src="images/person_2.jpg" alt="Image placeholder" />
    </div>
    <div class="comment-body" v-if="article.comment">
      <h3 v-if="article.comment.user_id">
        {{ article.comment.user_id.firstname }}
        {{ article.comment.user_id.lastname }}
      </h3>
      <div class="meta">January 9, 2018 at 2:21pm</div>
      <p>
        Lorem ipsum dolor sit amet, consectetur adipisicing elit. Pariatur
        quidem laborum necessitatibus, ipsam impedit vitae autem, eum officia,
        fugiat saepe enim sapiente iste iure! Quam voluptas earum impedit
        necessitatibus, nihil?
      </p>
      <p><a href="#" class="reply">Reply</a></p>
    </div>
  </li>
</template>
