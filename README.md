# NoteApp

This project was created with the goal of learning about UDF for navigation, domain layer, UI and Unit testing. Built entirely with Jetpack Compose and Kotlin. 🛠️

## UI

### Screenshots
![noteAppAddScreenWithoutInput](https://github.com/ozlembasabakar/CocktailApp/assets/53402156/6bedd2f4-b611-4366-bed9-ad932907f1f0)
![noteAppAddScreen](https://github.com/ozlembasabakar/CocktailApp/assets/53402156/6bc785ff-92db-4a2d-8b3c-7062ed4be33e)
![noteAppMainScreen](https://github.com/ozlembasabakar/CocktailApp/assets/53402156/3b4aab91-4d74-402c-93b8-24d3492891ae)

### Screenrecord
![noteAppScreenrecord](https://github.com/ozlembasabakar/CocktailApp/assets/53402156/1df8a1e4-9f1c-4134-9e95-10cd3298f702)

## UDF for navigation

>A unidirectional data flow (UDF) is a design pattern where state flows down and events flow up. By following unidirectional data flow, you can decouple composables that display state in the UI from the parts >of your app that store and change state.

  hbjkbkj

```
sealed class NotesAction {
    object OpenAddNewNoteScreen : NotesAction()
    data class OpenNoteDetail(val id: Int) : NotesAction()
}
```

```
addOrEditScreenViewModel.addOrEditAction.collectLatest {
    navController.navigate(Screen.NotesScreen.route)
}
```
































## Architecture
![Group 14](https://github.com/ozlembasabakar/NoteApp/assets/53402156/8e24dfa7-85d1-426a-8267-b53563e91ce2)

###
<table>
  <tr>
   <td><strong>Module</strong>
   </td>
   <td><strong>What was its purpose?</strong>
   </td>
   <td><strong>Classes</strong>
   </td>
  </tr>
  <tr>
   <td><code>app</code>
   </td>
   <td>Brings together the elements necessary for the application to work properly.
   </td>
   <td><code>HiltApplication</code><br>
   <code>NoteAppNavHost</code><br>
   </td>
  </tr>
  <tr>
   <td><code>feature:addoreditscreen</code><br></td>
   <td>Screen of editting a spesific note or adding new note.<br>
   </td>
   <td><code>AddOrEditScreen</code><br>
   <code>AddOrEditScreenViewModel</code>
   </td>
  </tr>
  <tr>
   <td><code>feature:notesscreen</code><br></td>
   <td>Screen of notes list.<br>
   </td>
   <td><code>NotesScreen</code><br>
   <code>NotesScreenViewModel</code>
   </td>
  </tr>
  <tr>
   <td><code>core:data</code>
   </td>
   <td>Collecting application data from various sources, such as local and used by the domain layer.
   </td>
   <td><code>NoteRepository</code><br>
      <code>NoteRepositoryImpl</code><br>
      <code>NoteRepositoryModule</code><br>
   </td>
  </tr>
  <tr>
   <td><code>core:database</code>
   </td>
   <td>Local database storage.
   </td>
   <td><code>NoteDao</code><br>
   <code>NoteDatabase</code><br>
   <code>NoteDatabaseModule</code>
  <code>NoteLocalDatasource</code>
   </td>
  </tr>
  <tr>
   <td><code>core:designsystem</code>
   </td>
   <td>Components, icons and theme components used throughout the app.
   </td>
   <td>
     <code>componenets</code> classes<br>
    <code>icon</code> classes<br>
    <code>theme</code> classes
    </td>
  </tr> 
    <tr>
   <td><code>core:domain</code>
   </td>
   <td>Use cases used as a bridge between <code>core:data</code> and <code>feature</code> modules.
   </td>
   <td><code>AddNewNoteUseCase</code><br>
    <code>DeleteNoteUseCase</code><br>
    <code>GetAllNotesUseCase</code><br>
      <code>GetNoteByIdUseCase</code><br>
      </td>
  </tr> 
  <tr>
   <td><code>core:model</code>
   </td>
   <td>Model classes used throughout the app.
   </td>
   <td>
     <code>Note</code><br>
     <code>AddOrEdit</code><br>
   </td>
  </tr>
</table>

### TODO

- [x] Users must be able to create notes with input fields such as title, description, image url (input can be optional) and store it locally on their phones.
- [x] Image url should be displayed as an Image in the listing screen.
- [x] Created note must contain a created date.
- [x] There must be a way to display all saved notes in the list. An item on the list must contain the created date (dd/mm/yyyy), the image if url is available, title and max. 2 lines of description.
- [x] There must be a way to edit/delete previously created notes.
- [x] All data should be persisted locally.
- [x] The design is mostly up to you as long as creating, listing and editing/deleting features are available to use.
- [x] Custom views.
- [x] Clear, defined architecture.
- [x] Multi module.
- [x] Gradle version catalog.
- [x] Apply the Material Design Guidelines as much as possible.
- [x] Unit tests.
- [x] Instrumented tests.








