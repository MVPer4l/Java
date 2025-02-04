import java.util.Arrays;

public class MusicEscales {

    public static void main(String[] args) {
        // TEST
        assert Arrays.equals(MusicEscales.getMusicEscale("major", "Fa#"),
                new String[] { "Fa#", "Sol#", "La#", "Si", "Do#", "Re#", "Fa" });
        // TEST_END

        // TEST
        assert Arrays.equals(MusicEscales.getMusicEscale("major", "La"),
                new String[] { "La", "Si", "Do#", "Re", "Mi", "Fa#", "Sol#" });
        // TEST_END


        // TEST
        assert Arrays.equals(MusicEscales.getMusicEscale("Major", "Do"),
                new String[] { "Do", "Re", "Mi", "Fa", "Sol", "La", "Si" });
        // TEST_END

        // TEST
        assert Arrays.equals(MusicEscales.getMusicEscale("Minor", "Do"),
                new String[] { "Do", "Re", "Re#", "Fa", "Sol","Sol#", "La#" });
        // TEST_END

        // TEST
        assert Arrays.equals(MusicEscales.getMusicEscale("minor", "La"),
                new String[] { "La", "Si", "Do", "Re", "Mi", "Fa", "Sol"});
        // TEST_END



        //TEST
        try {
            MusicEscales.getMusicEscale(null, "Do");
            assert false;
        } catch (IllegalArgumentException e) {
            assert true;
        }
        // TEST_END

        //TEST
        try {
            MusicEscales.getMusicEscale("major", null );
            assert false;
        } catch (IllegalArgumentException e) {
            assert true;
        }
        // TEST_END

        //TEST
        try {
            MusicEscales.getMusicEscale(null, "Do");
            assert false;
        } catch (IllegalArgumentException e) {
            assert true;
        }
        // TEST_END

        //TEST
        try {
            MusicEscales.getMusicEscale("Mixolid", "Re#");
            assert false;
        } catch (IllegalArgumentException e) {
            assert true;
        }
        // TEST_END

        //TEST
        try {
            MusicEscales.getMusicEscale("major", "Reb");
            assert false;
        } catch (IllegalArgumentException e) {
            assert true;
        }
        // TEST_END       
    }

    public static String[] getMusicEscale(String Escale, String Note) {

        if (Escale == null || Note == null) {

            throw new IllegalArgumentException("Escale and Note cannot be null");
        }

        if (!Escale.equalsIgnoreCase("major") && !Escale.equalsIgnoreCase("minor")) {

            throw new IllegalArgumentException("Music escale invalid");
        }

        if (Note != "Do" && Note != "Do#" && Note != "Re" && Note != "Re#" && Note != "Mi" && Note != "Fa"
                && Note != "Fa#" && Note != "Sol" && Note != "Sol#" && Note != "La" && Note != "La#" && Note != "Si") {

            throw new IllegalArgumentException("Invalid note");
        }

        String[] PianoNotes = { "Do", "Do#", "Re", "Re#", "Mi", "Fa", "Fa#", "Sol", "Sol#", "La", "La#", "Si" };
        int NotePosition = 0;
        int NumberOfNotes = PianoNotes.length;
        String[] ReacomodatedNotes = new String[PianoNotes.length];
        int index = 0;

        for (int i = 0; i < NumberOfNotes; i++) {
            if (PianoNotes[i] == Note) {
                NotePosition = i;
                break;
            }
        }

        for (int j = NotePosition; j < PianoNotes.length; j++) {

            ReacomodatedNotes[index++] = PianoNotes[j];

        }

        for (int k = 0; k < NotePosition; k++) {

            ReacomodatedNotes[index++] = PianoNotes[k];

        }

        switch (Escale.toLowerCase()) {
            case "major":

                String[] MajorEscaleOfNote = { ReacomodatedNotes[0], ReacomodatedNotes[2], ReacomodatedNotes[4],
                        ReacomodatedNotes[5], ReacomodatedNotes[7], ReacomodatedNotes[9], ReacomodatedNotes[11] };

                System.out.println(Arrays.toString(MajorEscaleOfNote));
                return (MajorEscaleOfNote);

            case "minor":

                String[] MinorEscaleOfNote = { ReacomodatedNotes[0], ReacomodatedNotes[2], ReacomodatedNotes[3],
                        ReacomodatedNotes[5], ReacomodatedNotes[7], ReacomodatedNotes[8], ReacomodatedNotes[10] };

                System.out.println(Arrays.toString(MinorEscaleOfNote));
                return (MinorEscaleOfNote);

            default:
                throw new IllegalArgumentException("Invalid Operation");
        }

    }

}
