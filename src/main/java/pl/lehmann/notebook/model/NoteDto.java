package pl.lehmann.notebook.model;

public class NoteDto {
    private Long id;
    private String title;
    private String author;
    private String message;
    private String date;
    private String answer;

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

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public static NoteDto fromNote(Note note) {
        NoteDto noteDto = new NoteDto();
        noteDto.setId(note.getId());
        noteDto.setAuthor(note.getAuthor());
        noteDto.setDate(note.getDate());
        noteDto.setMessage(note.getMessage());
        noteDto.setTitle(note.getTitle());
        return noteDto;
    }
}
