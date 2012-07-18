<c:if test="${hasPrev}">
	<a href="?page=${page-1 }">&lt;次へ</a>
</c:if>
<c:if test="${hasNext}">
	<a href="?page=${page+1 }">前へ&gt;</a>
</c:if>