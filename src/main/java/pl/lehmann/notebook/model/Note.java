package pl.lehmann.notebook.model;


import javax.persistence.*;

@Entity
@Table(name = "notes")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    @Column(columnDefinition = "TEXT")
    private String message;
    private String date;

    public Note(String title, String author, String message, String date) {
        this.title = title;
        this.author = author;
        this.message = message;
        this.date = date;
    }

    public Note() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", note='" + message + '\'' +
                ", date=" + date +
                '}';
    }

    public static Note fromNoteDto(NoteDto noteDto) {
        Note note = new Note();
        note.setAuthor(noteDto.getAuthor());
        note.setDate(noteDto.getDate());
        note.setMessage(noteDto.getMessage());
        note.setTitle(noteDto.getTitle());
        note.setId(noteDto.getId());
        return note;
    }
}
