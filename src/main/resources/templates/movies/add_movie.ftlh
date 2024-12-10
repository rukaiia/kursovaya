<#import "../layout.ftlh" as main>
<@main.layout>

    <form action="/add" class="mb-5" method="post">
        <input type="text" name="name" class="form-control" placeholder="Title">
        <input type="number" name="releaseYear" class="form-control" placeholder="Release year">
        <div class="mb-3">
            <label for="description">Description</label>
            <textarea type="text" id="description" name="description" class="form-control" rows="3"></textarea>
        </div>
        <input type="text" name="director" class="form-control" placeholder="Director">
        <input type="text" name="cast_member_name" class="form-control" placeholder="Cast member name">
        <input type="text" name="cast_member_role" class="form-control" placeholder="Role">

        <button type="submit" class="btn btn-success">Create</button>
    </form>

</@main.layout>