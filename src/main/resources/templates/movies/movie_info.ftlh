<#import "../layout.ftlh" as main>
<@main.layout>
<#--    <div class="row">-->
    <div class="card" style="width: 18rem;">
        <div class="card-body">
            <input type="hidden" value="${movie.id}" id="movieId">
            <h5 class="card-title">${movie.name}</h5>
            <h6 class="card-subtitle mb-2 text-muted">${movie.director.fullName}</h6>
            <h6 class="card-subtitle mb-2 text-muted">${movie.releaseYear?string.number}</h6>
            <p class="card-text">${movie.description}</p>
            <hr>
            <h6 class="card-subtitle mb-2 text-muted">Cast:</h6>
            <#list movie.castMembers as item>
                <p class="card-text">${item.fullName} - ${item.role}</p>
            </#list>
        </div>
    </div>
<#--    </div>-->

    <hr>

    <div id="review_block">
        <form id="review_form">
            <div class="form-group">
                <label for="rating">Rating</label>
                <select class="form-control" id="rating" name="rating">
                    <option>1</option>
                    <option>2</option>
                    <option>3</option>
                    <option>4</option>
                    <option>5</option>
                </select>
            </div>
            <div class="form-group">
                <label for="comment">Comment</label>
                <textarea class="form-control" id="comment" rows="3" name="comment"></textarea>
            </div>
            <button type="submit" class="btn btn-success">Success</button>
        </form>

        <#list reviews as review>
            <div class="card my-3 review-card">
                <div class="card-header">
                    ${review.reviewer}
                </div>
                <div class="card-body">
                    <blockquote class="blockquote mb-0">
                        <p>${review.comment}</p>
                        <footer class="blockquote-footer">
                            ${review.createTime}
                        </footer>
                    </blockquote>
                </div>
            </div>
        </#list>
    </div>
    <script>
        function reviewHandler(e) {
            e.preventDefault()
            let form = e.target
            let data = new FormData(form)

            data.append('movieId', ${movie.id})

            let json = JSON.stringify(Object.fromEntries(data))

            try {
                makeRequest(
                    baseUrl + '/reviews',
                    updateOptions({method: 'POST', body: json})
                )
            } catch (e) {
                console.error(e)
            }
        }

        document.getElementById('review_form')
            .addEventListener('submit', reviewHandler)

        window.addEventListener('load', async () => {
            setInterval(async () => {
                let response = JSON.stringify(await makeRequest(
                    baseUrl + '/reviews/${movie.id}'
                ))
                let review = JSON.parse(response)
                fillBlock(review)
            }, 10000)
        })

        function fillBlock(review) {
            let reviewBlock = document.getElementById('review_block')
            let reviewCard = document.getElementsByClassName('review-card')
            let lastElement = Array.from(reviewCard).pop()
            for (let reviewElement of review) {
                let lastReviewDateTime
                let createTime = new Date(reviewElement.createTime)
                if (lastElement) {
                    let value = lastElement.getElementsByTagName('footer')[0].innerText
                    lastReviewDateTime = new Date(value)
                } else {
                    let date = new Date()
                    lastReviewDateTime = date.setFullYear(date.getFullYear() - 1)
                }

                if (compareDates(createTime, lastReviewDateTime)) {
                    let element = createBlock(reviewElement, reviewCard[0])
                    reviewBlock.append(element)
                }
            }
        }

        function compareDates(d1, d2) {
            return d1.getTime() > d2.getTime()
        }

        function createBlock(reviewElement, node) {
            let element = node.cloneNode(true)

            let cardHeader = element.getElementsByClassName('card-header')[0]
            cardHeader.innerText = reviewElement.reviewer

            let p = element.getElementsByTagName('p')[0]
            p.innerText = reviewElement.comment

            let footer = element.getElementsByTagName('footer')[0]
            footer.innerText = reviewElement.createTime

            return element
        }
    </script>
</@main.layout>