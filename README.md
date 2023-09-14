# NoteApp

This project was created with the goal of learning about UDF for navigation, domain layer, Instrumented and Unit testing. Built entirely with Jetpack Compose and Kotlin. 🛠️

## UI

### Screenshots
![noteAppAddScreenWithoutInput](https://github.com/ozlembasabakar/CocktailApp/assets/53402156/6bedd2f4-b611-4366-bed9-ad932907f1f0)
![noteAppAddScreen](https://github.com/ozlembasabakar/CocktailApp/assets/53402156/6bc785ff-92db-4a2d-8b3c-7062ed4be33e)
![noteAppMainScreen](https://github.com/ozlembasabakar/CocktailApp/assets/53402156/3b4aab91-4d74-402c-93b8-24d3492891ae)

### Screenrecord
![noteAppScreenrecord](https://github.com/ozlembasabakar/CocktailApp/assets/53402156/1df8a1e4-9f1c-4134-9e95-10cd3298f702)

## UDF for navigation

>A unidirectional data flow (UDF) is a design pattern where state flows down and events flow up. By following unidirectional data flow, you can decouple composables that display state in the UI from the parts >of your app that store and change state.

By following Unidirectional Data Flow approach, you achieve a clear separation of concerns. The navigation logic is decoupled from the UI components, making it easier to understand, test, and maintain. 

Additionally, the predictability of data flow simplifies debugging, as you can trace the actions, reducers, and state changes throughout the navigation process.

Using a type of flow can assist in implementing this unidirectional data flow pattern in Android applications.

Unidirectional Data Flow approach in navigation offers a structured and efficient way to handle navigation and data flow in Android applications, leading to more maintainable and scalable codebases.

> ViewModel events are actions originated from the ViewModel that the UI should perform. For example, displaying an informative message to the user, or navigating to a different screen when the application state changes.
>
> Our guidance on ViewModel events is opinionated in two different ways:
>
> 1. Whenever a one-off event originates in the ViewModel, the ViewModel should handle that event immediately causing a state update. The ViewModel should only expose application state. Exposing events that haven’t been reduced to state from a ViewModel means the ViewModel is not the source of truth for the state derived from those events; Unidirectional Data Flow (UDF) describes the advantages of sending events only to consumers that outlive their producers.
> 2. State should be exposed using an observable data holder type.

In the application, ViewModel events can be shown to the UI using Channels or other flows such as SharedFlow.

ViewModel events that cause a UI status update should be handled immediately.

![Group 17](https://github.com/ozlembasabakar/NoteApp/assets/53402156/d33b765e-738d-4e04-931c-1f956b868c6a)

Below is an example of going back to the home screen from the add or change annotation screen in the app.
```
sealed class AddOrEditAction {
    object NavigateBack : AddOrEditAction()
}
```
```
...
    addOrEditScreenViewModel.addOrEditAction.collectLatest {
        navController.navigate(Screen.NotesScreen.route)
    }
...
```
## Architecture

![Group 18](https://github.com/ozlembasabakar/NoteApp/assets/53402156/1b241354-1d92-40ad-8c39-3025c05fb92c)

### Domain Layer
The domain layer is an essential component of the Android architecture, which follows the principles of Clean Architecture or Domain-Driven Design (DDD). It represents the core business logic and rules of the application, independent of any specific framework or technology.

Using the domain layer allows you not only to allow direct access to the data layer from the UI layer, but also to do everything through the domain layer.

> This layer is optional because not all apps will have these requirements. You should only use it when needed-for example, to handle complexity or favor reusability.

The repeatable business logic found in the UI layer should be encapsulated in a use case class. This makes it easy to implement any changes. It also allows to test the logic alone.

The UI layer should not know the operations in the <code>GetAllNotesUseCase</code> class, where or how the notes are taken. Even if the method of taking notes is changed with this structure, the UI will not be aware of it. And continues to display notes.

## Testing
There are many types of tests. These are the tests that should be considered differently according to their *subject* or *scope*.

For subject:
> **Functional testing:** does my app do what it's supposed to?
> **Performance testing:** does it do it quickly and efficiently?
> **Accessibility testing:** does it work well with accessibility services?
> **Compatibility testing:** does it work well on every device and API level?

For scpoe:
> **Unit tests** or **small tests** only verify a very small portion of the app, such as a method or class.
> **End-to-end tests** or **big tests** verify larger parts of the app at the same time, such as a whole screen or user flow.
> **Medium tests** are in between and check the **integration** between two or more units.

### Unit Test
The purpose of Unit Testing is to verify the behavior and accuracy of small, isolated units of code, regardless of their integration with other components or external dependencies. By testing these units individually, errors can be caught and their functionality can be trusted by ensuring that individual code units work as intended.

> Unit tests or small tests only verify a very small portion of the app, such as a method or class.

```
@Test
fun `when the note deleted, should delete from repository`() = runTest {

    // Given an instance of NotesScreenViewModel
    val viewModel = notesScreenViewModel()
    val note = mock<Note>()

    // When note is deleted
    viewModel.deleteNote(note)

    // Then it should be deleting note
    verify(deleteNoteUseCase).invoke(note)
}
```

### Instrumented Test

> Instrumented test, also known as instrumentation tests, are initialized in a special environment that gives them access to an instance of Instrumentation.

With instrumented tests, the goal is to verify the user's actions, such as clicking a button, typing into an EditText.

```
@Test
fun whenPressedDeleteIcon_IsNoteDeletedFromList() {

    // When the add button is clicked
    composeTestRule.onNodeWithTag("Button").performClick()

    // When the input text field is clicked and entered title value
    composeTestRule.onNodeWithTag("Title").performClick()
    composeTestRule.onNodeWithTag("Title").performTextInput(note.title)

    // When the input back button is clicked
    composeTestRule.onNodeWithTag("BackButton").performClick()

    // When the input delete button is clicked
    composeTestRule.onNodeWithTag("Delete").performClick()

    // Then the note is not displayed
    composeTestRule.onNodeWithTag("Card").assertDoesNotExist()
}
```

When one part of a function, class, or module is separated from the others, it will be easier and more effective to test. This practice is known as *decoupling* and is the most important concept for testable architecture.

Common decoupling techniques can be:
- Separating an application into layers such as UI, Domain, and Data. Or breaking it up into modules, one per feature.
- Avoiding adding logic to entities with large dependencies (like Activity or Fragment). Using these classes as entry points to the framework and moving the UI and business logic elsewhere like Composable, ViewModel or domain layer.

<br></br>
<table>
  <tr>
   <td><strong>Layer</strong>
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
